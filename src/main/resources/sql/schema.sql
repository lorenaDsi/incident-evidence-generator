-- ============================================================================
-- SCHEMA SQL SERVER - INCIDENT EVIDENCE GENERATOR
-- ============================================================================
-- Script de creación de tablas y estructuras base para la aplicación
-- de generación de evidencia documental de incidencias.
-- ============================================================================

USE [IncidenceDB];
GO

-- ============================================================================
-- 1. TABLA: AREAS
-- ============================================================================
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Areas' AND type = 'U')
BEGIN
    CREATE TABLE [dbo].[Areas]
    (
        [AreaId] INT PRIMARY KEY IDENTITY(1,1),
        [AreaCode] VARCHAR(50) NOT NULL UNIQUE,
        [AreaName] VARCHAR(255) NOT NULL,
        [Description] VARCHAR(MAX),
        [IsActive] BIT DEFAULT 1,
        [CreatedAt] DATETIME2 DEFAULT GETDATE(),
        [ModifiedAt] DATETIME2 DEFAULT GETDATE()
    );
    
    CREATE CLUSTERED INDEX [IX_Areas_AreaCode] ON [dbo].[Areas] ([AreaCode]);
    PRINT 'Tabla Areas creada correctamente';
END
GO

-- ============================================================================
-- 2. TABLA: INCIDENCES (Incidencias)
-- ============================================================================
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Incidences' AND type = 'U')
BEGIN
    CREATE TABLE [dbo].[Incidences]
    (
        [IncidenceId] BIGINT PRIMARY KEY IDENTITY(1,1),
        [IncidenceNumber] VARCHAR(50) NOT NULL UNIQUE,
        [AreaId] INT NOT NULL,
        [IncidenceDate] DATE NOT NULL,
        [IncidenceDateTime] DATETIME2 NOT NULL,
        [Status] VARCHAR(50) DEFAULT 'OPEN',
        [Description] VARCHAR(MAX),
        [CreatedAt] DATETIME2 DEFAULT GETDATE(),
        [ModifiedAt] DATETIME2 DEFAULT GETDATE(),
        
        CONSTRAINT [FK_Incidences_Areas] FOREIGN KEY ([AreaId])
            REFERENCES [dbo].[Areas]([AreaId]) ON DELETE CASCADE
    );
    
    CREATE NONCLUSTERED INDEX [IX_Incidences_AreaId_Date] 
        ON [dbo].[Incidences] ([AreaId], [IncidenceDate])
        INCLUDE ([IncidenceNumber], [Status]);
    
    CREATE NONCLUSTERED INDEX [IX_Incidences_IncidenceDate] 
        ON [dbo].[Incidences] ([IncidenceDate]);
    
    CREATE NONCLUSTERED INDEX [IX_Incidences_IncidenceNumber] 
        ON [dbo].[Incidences] ([IncidenceNumber]);
    
    PRINT 'Tabla Incidences creada correctamente';
END
GO

-- ============================================================================
-- 3. TABLA: DOCUMENTS (Documentos)
-- ============================================================================
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Documents' AND type = 'U')
BEGIN
    CREATE TABLE [dbo].[Documents]
    (
        [DocumentId] BIGINT PRIMARY KEY IDENTITY(1,1),
        [IncidenceId] BIGINT NOT NULL,
        [DocumentPath] VARCHAR(MAX) NOT NULL,
        [FileName] VARCHAR(512),
        [FileExtension] VARCHAR(10),
        [FileSize] BIGINT,
        [DocumentType] VARCHAR(50),
        [IsActive] BIT DEFAULT 1,
        [CreatedAt] DATETIME2 DEFAULT GETDATE(),
        [ModifiedAt] DATETIME2 DEFAULT GETDATE(),
        
        CONSTRAINT [FK_Documents_Incidences] FOREIGN KEY ([IncidenceId])
            REFERENCES [dbo].[Incidences]([IncidenceId]) ON DELETE CASCADE
    );
    
    CREATE NONCLUSTERED INDEX [IX_Documents_IncidenceId] 
        ON [dbo].[Documents] ([IncidenceId]);
    
    CREATE NONCLUSTERED INDEX [IX_Documents_DocumentPath] 
        ON [dbo].[Documents] ([DocumentPath]);
    
    PRINT 'Tabla Documents creada correctamente';
END
GO

-- ============================================================================
-- 4. TABLA: PROCESS_LOGS (Logs de Procesos)
-- ============================================================================
IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ProcessLogs' AND type = 'U')
BEGIN
    CREATE TABLE [dbo].[ProcessLogs]
    (
        [LogId] BIGINT PRIMARY KEY IDENTITY(1,1),
        [ProcessId] VARCHAR(36),
        [ProcessName] VARCHAR(255),
        [ProcessStartTime] DATETIME2,
        [ProcessEndTime] DATETIME2,
        [Status] VARCHAR(50),
        [TotalIncidences] INT,
        [TotalDocuments] INT,
        [DocumentsFound] INT,
        [DocumentsMissing] INT,
        [ExcelFileName] VARCHAR(512),
        [ZipFileName] VARCHAR(512),
        [ErrorMessage] VARCHAR(MAX),
        [CreatedAt] DATETIME2 DEFAULT GETDATE()
    );
    
    CREATE NONCLUSTERED INDEX [IX_ProcessLogs_ProcessId] 
        ON [dbo].[ProcessLogs] ([ProcessId]);
    
    CREATE NONCLUSTERED INDEX [IX_ProcessLogs_ProcessStartTime] 
        ON [dbo].[ProcessLogs] ([ProcessStartTime]);
    
    PRINT 'Tabla ProcessLogs creada correctamente';
END
GO

-- ============================================================================
-- 5. VERÍFICACIÓN DE TABLAS
-- ============================================================================
PRINT '';
PRINT 'Verificando tablas creadas:';
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo';
GO
