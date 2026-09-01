-- ============================================================================
-- INDICES SQL SERVER - INCIDENT EVIDENCE GENERATOR
-- ============================================================================
-- Script de optimización con índices avanzados para consultas de alto rendimiento
-- ============================================================================

USE [IncidenceDB];
GO

-- ============================================================================
-- 1. ÍNDICES COMPUESTOS (COMPOSITE INDEXES)
-- ============================================================================

-- Índice para búsquedas de incidencias por área y fecha
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Incidences_Area_Date_Status')
BEGIN
    CREATE NONCLUSTERED INDEX [IX_Incidences_Area_Date_Status]
    ON [dbo].[Incidences] ([AreaId], [IncidenceDate], [Status])
    INCLUDE ([IncidenceNumber], [IncidenceDateTime])
    WITH (FILLFACTOR = 90);
    PRINT '✓ Índice IX_Incidences_Area_Date_Status creado';
END
GO

-- Índice para búsquedas de documentos por incidencia
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Documents_IncidenceId_Active')
BEGIN
    CREATE NONCLUSTERED INDEX [IX_Documents_IncidenceId_Active]
    ON [dbo].[Documents] ([IncidenceId], [IsActive])
    INCLUDE ([DocumentPath], [FileName])
    WITH (FILLFACTOR = 90);
    PRINT '✓ Índice IX_Documents_IncidenceId_Active creado';
END
GO

-- ============================================================================
-- 2. ÍNDICES COVERING (COVERING INDEXES)
-- ============================================================================

-- Índice covering para principal consulta de reporte
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_ReportQuery_Covering')
BEGIN
    CREATE NONCLUSTERED INDEX [IX_ReportQuery_Covering]
    ON [dbo].[Incidences] ([AreaId], [IncidenceDate])
    INCLUDE ([IncidenceNumber], [IncidenceDateTime], [Status])
    WITH (FILLFACTOR = 90, STATISTICS_NORECOMPUTE = OFF);
    PRINT '✓ Índice IX_ReportQuery_Covering creado';
END
GO

-- ============================================================================
-- 3. ÍNDICES FILTRADOS (FILTERED INDEXES)
-- ============================================================================

-- Índice filtrado para incidencias activas
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Incidences_Active_Only')
BEGIN
    CREATE NONCLUSTERED INDEX [IX_Incidences_Active_Only]
    ON [dbo].[Incidences] ([AreaId], [IncidenceDate])
    WHERE [Status] = 'OPEN'
    WITH (FILLFACTOR = 90);
    PRINT '✓ Índice IX_Incidences_Active_Only creado';
END
GO

-- Índice filtrado para documentos activos
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Documents_Active_Only')
BEGIN
    CREATE NONCLUSTERED INDEX [IX_Documents_Active_Only]
    ON [dbo].[Documents] ([IncidenceId])
    INCLUDE ([DocumentPath], [FileName])
    WHERE [IsActive] = 1
    WITH (FILLFACTOR = 90);
    PRINT '✓ Índice IX_Documents_Active_Only creado';
END
GO

-- ============================================================================
-- 4. ÍNDICES PARA JOIN OPERATIONS
-- ============================================================================

-- Índice para optimizar JOIN Areas-Incidences
IF NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = 'IX_Areas_Lookup')
BEGIN
    CREATE NONCLUSTERED INDEX [IX_Areas_Lookup]
    ON [dbo].[Areas] ([IsActive])
    INCLUDE ([AreaCode], [AreaName])
    WITH (FILLFACTOR = 95);
    PRINT '✓ Índice IX_Areas_Lookup creado';
END
GO

-- ============================================================================
-- 5. ESTADÍSTICAS
-- ============================================================================

-- Actualizar estadísticas
UPDATE STATISTICS [dbo].[Areas];
UPDATE STATISTICS [dbo].[Incidences];
UPDATE STATISTICS [dbo].[Documents];
UPDATE STATISTICS [dbo].[ProcessLogs];

PRINT '';
PRINT 'Estadísticas actualizadas';
GO

-- ============================================================================
-- 6. VERIFICACIÓN DE ÍNDICES
-- ============================================================================

PRINT '';
PRINT 'Lista de Índices Creados:';
SELECT 
    t.name AS Tabla,
    i.name AS Índice,
    i.type_desc AS Tipo,
    CASE WHEN i.is_primary_key = 1 THEN 'PRIMARY KEY' ELSE '' END AS Restricción
FROM sys.indexes i
JOIN sys.tables t ON i.object_id = t.object_id
WHERE t.schema_id = SCHEMA_ID('dbo')
ORDER BY t.name, i.name;
GO
