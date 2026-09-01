-- ============================================================================
-- CONSULTAS SQL SERVER - INCIDENT EVIDENCE GENERATOR
-- ============================================================================
-- Consultas optimizadas para la generación de reportes de evidencia documental
-- ============================================================================

USE [IncidenceDB];
GO

-- ============================================================================
-- CONSULTA PRINCIPAL: Obtener Incidencias y Documentos Asociados
-- ============================================================================
-- Descripción: Retorna todas las incidencias de áreas específicas en un rango
--              de fechas, con sus documentos asociados.
-- Parámetros: @Areas (CSV), @DateFrom, @DateTo, @PageNumber, @PageSize
-- Rendimiento: O(log n) con índices covering
-- ============================================================================

CREATE OR ALTER PROCEDURE [dbo].[sp_GetIncidenceDocuments]
    @Areas NVARCHAR(MAX),      -- CSV: 'AREA51,AREA52,AREA53'
    @DateFrom DATE,
    @DateTo DATE,
    @PageNumber INT = 1,
    @PageSize INT = 1000
AS
BEGIN
    SET NOCOUNT ON;
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
    
    DECLARE @StartRow INT = (@PageNumber - 1) * @PageSize;
    
    -- Convertir CSV a tabla temporal
    DECLARE @AreaTable TABLE (AreaCode VARCHAR(50));
    INSERT INTO @AreaTable (AreaCode)
    SELECT TRIM(value) FROM STRING_SPLIT(@Areas, ',')
    WHERE TRIM(value) <> '';
    
    -- Consulta principal con paginación
    SELECT 
        a.[AreaCode],
        inc.[IncidenceNumber],
        inc.[IncidenceDate],
        doc.[DocumentPath],
        doc.[FileName],
        doc.[DocumentId],
        doc.[FileSize]
    FROM [dbo].[Incidences] inc
    INNER JOIN [dbo].[Areas] a ON inc.[AreaId] = a.[AreaId]
    LEFT JOIN [dbo].[Documents] doc ON inc.[IncidenceId] = doc.[IncidenceId]
        AND doc.[IsActive] = 1
    WHERE a.[AreaCode] IN (SELECT AreaCode FROM @AreaTable)
        AND inc.[IncidenceDate] BETWEEN @DateFrom AND @DateTo
        AND inc.[Status] = 'OPEN'
    ORDER BY 
        a.[AreaCode],
        inc.[IncidenceNumber],
        doc.[DocumentId]
    OFFSET @StartRow ROWS
    FETCH NEXT @PageSize ROWS ONLY;
    
END;
GO

-- ============================================================================
-- CONSULTA SECUNDARIA: Contar Total de Incidencias
-- ============================================================================
-- Descripción: Retorna el número total de incidencias para paginación
-- ============================================================================

CREATE OR ALTER PROCEDURE [dbo].[sp_CountIncidences]
    @Areas NVARCHAR(MAX),
    @DateFrom DATE,
    @DateTo DATE
AS
BEGIN
    SET NOCOUNT ON;
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
    
    DECLARE @AreaTable TABLE (AreaCode VARCHAR(50));
    INSERT INTO @AreaTable (AreaCode)
    SELECT TRIM(value) FROM STRING_SPLIT(@Areas, ',')
    WHERE TRIM(value) <> '';
    
    SELECT COUNT(DISTINCT inc.[IncidenceId]) AS TotalIncidences
    FROM [dbo].[Incidences] inc
    INNER JOIN [dbo].[Areas] a ON inc.[AreaId] = a.[AreaId]
    WHERE a.[AreaCode] IN (SELECT AreaCode FROM @AreaTable)
        AND inc.[IncidenceDate] BETWEEN @DateFrom AND @DateTo
        AND inc.[Status] = 'OPEN';
    
END;
GO

-- ============================================================================
-- CONSULTA SECUNDARIA: Contar Total de Documentos
-- ============================================================================

CREATE OR ALTER PROCEDURE [dbo].[sp_CountDocuments]
    @Areas NVARCHAR(MAX),
    @DateFrom DATE,
    @DateTo DATE
AS
BEGIN
    SET NOCOUNT ON;
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
    
    DECLARE @AreaTable TABLE (AreaCode VARCHAR(50));
    INSERT INTO @AreaTable (AreaCode)
    SELECT TRIM(value) FROM STRING_SPLIT(@Areas, ',')
    WHERE TRIM(value) <> '';
    
    SELECT COUNT(doc.[DocumentId]) AS TotalDocuments
    FROM [dbo].[Documents] doc
    INNER JOIN [dbo].[Incidences] inc ON doc.[IncidenceId] = inc.[IncidenceId]
    INNER JOIN [dbo].[Areas] a ON inc.[AreaId] = a.[AreaId]
    WHERE a.[AreaCode] IN (SELECT AreaCode FROM @AreaTable)
        AND inc.[IncidenceDate] BETWEEN @DateFrom AND @DateTo
        AND inc.[Status] = 'OPEN'
        AND doc.[IsActive] = 1;
    
END;
GO

-- ============================================================================
-- CONSULTA DE REPORTE: Incidencias y Documentos Agrupados
-- ============================================================================
-- Descripción: Retorna reporte resumido por área e incidencia
-- ============================================================================

CREATE OR ALTER PROCEDURE [dbo].[sp_GetReportSummary]
    @Areas NVARCHAR(MAX),
    @DateFrom DATE,
    @DateTo DATE
AS
BEGIN
    SET NOCOUNT ON;
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
    
    DECLARE @AreaTable TABLE (AreaCode VARCHAR(50));
    INSERT INTO @AreaTable (AreaCode)
    SELECT TRIM(value) FROM STRING_SPLIT(@Areas, ',')
    WHERE TRIM(value) <> '';
    
    SELECT 
        a.[AreaCode],
        COUNT(DISTINCT inc.[IncidenceId]) AS TotalIncidences,
        COUNT(doc.[DocumentId]) AS TotalDocuments,
        SUM(ISNULL(doc.[FileSize], 0)) AS TotalSize
    FROM [dbo].[Incidences] inc
    INNER JOIN [dbo].[Areas] a ON inc.[AreaId] = a.[AreaId]
    LEFT JOIN [dbo].[Documents] doc ON inc.[IncidenceId] = doc.[IncidenceId]
        AND doc.[IsActive] = 1
    WHERE a.[AreaCode] IN (SELECT AreaCode FROM @AreaTable)
        AND inc.[IncidenceDate] BETWEEN @DateFrom AND @DateTo
        AND inc.[Status] = 'OPEN'
    GROUP BY a.[AreaCode]
    ORDER BY a.[AreaCode];
    
END;
GO

-- ============================================================================
-- PROCEDIMIENTO: Registrar Proceso
-- ============================================================================

CREATE OR ALTER PROCEDURE [dbo].[sp_LogProcess]
    @ProcessId VARCHAR(36),
    @TotalIncidences INT,
    @TotalDocuments INT,
    @DocumentsFound INT,
    @DocumentsMissing INT,
    @ExcelFileName VARCHAR(512),
    @ZipFileName VARCHAR(512),
    @Status VARCHAR(50),
    @ErrorMessage VARCHAR(MAX) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    
    INSERT INTO [dbo].[ProcessLogs]
    (
        [ProcessId],
        [ProcessName],
        [ProcessStartTime],
        [ProcessEndTime],
        [Status],
        [TotalIncidences],
        [TotalDocuments],
        [DocumentsFound],
        [DocumentsMissing],
        [ExcelFileName],
        [ZipFileName],
        [ErrorMessage]
    )
    VALUES
    (
        @ProcessId,
        'IncidentEvidenceGeneration',
        GETDATE(),
        GETDATE(),
        @Status,
        @TotalIncidences,
        @TotalDocuments,
        @DocumentsFound,
        @DocumentsMissing,
        @ExcelFileName,
        @ZipFileName,
        @ErrorMessage
    );
    
END;
GO

PRINT 'Todos los procedimientos almacenados creados correctamente';
GO
