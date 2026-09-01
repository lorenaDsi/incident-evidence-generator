-- ============================================================================
-- DATOS DE EJEMPLO - INCIDENT EVIDENCE GENERATOR
-- ============================================================================
-- Script para poblar la base de datos con datos de ejemplo para testing
-- ============================================================================

USE [IncidenceDB];
GO

-- ============================================================================
-- 1. INSERTAR ÁREAS DE EJEMPLO
-- ============================================================================

INSERT INTO [dbo].[Areas] ([AreaCode], [AreaName], [Description], [IsActive])
VALUES
    ('AREA51', 'Área de Operaciones 51', 'Centro de Operaciones Principal', 1),
    ('AREA52', 'Área de Soporte Técnico', 'Equipo de Soporte Técnico', 1),
    ('AREA53', 'Área de Recursos Humanos', 'Departamento de RRHH', 1),
    ('AREA54', 'Área de Finanzas', 'Departamento Financiero', 1),
    ('AREA55', 'Área de Cumplimiento', 'Departamento de Cumplimiento Normativo', 1);

PRINT 'Insertadas 5 áreas de ejemplo';
GO

-- ============================================================================
-- 2. INSERTAR INCIDENCIAS DE EJEMPLO
-- ============================================================================

INSERT INTO [dbo].[Incidences] 
    ([IncidenceNumber], [AreaId], [IncidenceDate], [IncidenceDateTime], [Status], [Description])
VALUES
    ('INC-2026-08-001', 1, '2026-08-01', '2026-08-01 10:30:00', 'OPEN', 'Fallo en sistema de transacciones'),
    ('INC-2026-08-002', 1, '2026-08-01', '2026-08-01 14:15:00', 'OPEN', 'Problema de conectividad'),
    ('INC-2026-08-003', 2, '2026-08-02', '2026-08-02 09:00:00', 'OPEN', 'Error en aplicación cliente'),
    ('INC-2026-08-004', 2, '2026-08-03', '2026-08-03 11:45:00', 'OPEN', 'Requiere configuración'),
    ('INC-2026-08-005', 3, '2026-08-05', '2026-08-05 15:30:00', 'OPEN', 'Solicitud de acceso'),
    ('INC-2026-08-006', 1, '2026-08-10', '2026-08-10 08:00:00', 'OPEN', 'Mantenimiento preventivo'),
    ('INC-2026-08-007', 4, '2026-08-15', '2026-08-15 13:20:00', 'OPEN', 'Discrepancia en reportes'),
    ('INC-2026-08-008', 5, '2026-08-20', '2026-08-20 10:00:00', 'OPEN', 'Auditóría requerida'),
    ('INC-2026-08-009', 1, '2026-08-22', '2026-08-22 16:45:00', 'OPEN', 'Caida del servicio'),
    ('INC-2026-08-010', 2, '2026-08-25', '2026-08-25 12:15:00', 'OPEN', 'Error de sincronización');

PRINT 'Insertadas 10 incidencias de ejemplo';
GO

-- ============================================================================
-- 3. INSERTAR DOCUMENTOS DE EJEMPLO
-- ============================================================================

INSERT INTO [dbo].[Documents]
    ([IncidenceId], [DocumentPath], [FileName], [FileExtension], [FileSize], [DocumentType], [IsActive])
VALUES
    (1, '/ruta/I_2026/08/01/INC-2026-08-001/reporte_inicial.pdf', 'reporte_inicial.pdf', '.pdf', 245632, 'PDF', 1),
    (1, '/ruta/I_2026/08/01/INC-2026-08-001/evidencia_01.png', 'evidencia_01.png', '.png', 512000, 'IMAGE', 1),
    (1, '/ruta/I_2026/08/01/INC-2026-08-001/logs.txt', 'logs.txt', '.txt', 45123, 'LOG', 1),
    (2, '/ruta/I_2026/08/01/INC-2026-08-002/conexion_error.log', 'conexion_error.log', '.log', 87654, 'LOG', 1),
    (2, '/ruta/I_2026/08/01/INC-2026-08-002/stack_trace.txt', 'stack_trace.txt', '.txt', 23456, 'LOG', 1),
    (3, '/ruta/I_2026/08/02/INC-2026-08-003/archivo_no_existe.pdf', 'archivo_no_existe.pdf', '.pdf', NULL, 'PDF', 1),
    (3, '/ruta/I_2026/08/02/INC-2026-08-003/screenshot.jpg', 'screenshot.jpg', '.jpg', 1024000, 'IMAGE', 1),
    (4, '/ruta/I_2026/08/03/INC-2026-08-004/guia_configuracion.docx', 'guia_configuracion.docx', '.docx', 156000, 'DOCUMENT', 1),
    (5, '/ruta/I_2026/08/05/INC-2026-08-005/solicitud_acceso.pdf', 'solicitud_acceso.pdf', '.pdf', 65000, 'PDF', 1),
    (5, '/ruta/I_2026/08/05/INC-2026-08-005/aprobacion.xlsx', 'aprobacion.xlsx', '.xlsx', 45000, 'SPREADSHEET', 1),
    (6, '/ruta/I_2026/08/10/INC-2026-08-006/cronograma.pdf', 'cronograma.pdf', '.pdf', 78000, 'PDF', 1),
    (7, '/ruta/I_2026/08/15/INC-2026-08-007/analisis_discrepancias.xlsx', 'analisis_discrepancias.xlsx', '.xlsx', 234000, 'SPREADSHEET', 1),
    (8, '/ruta/I_2026/08/20/INC-2026-08-008/informe_auditoria.pdf', 'informe_auditoria.pdf', '.pdf', 567000, 'PDF', 1),
    (9, '/ruta/I_2026/08/22/INC-2026-08-009/diagnostico.txt', 'diagnostico.txt', '.txt', 12000, 'LOG', 1),
    (10, '/ruta/I_2026/08/25/INC-2026-08-010/reporte_sincronizacion.log', 'reporte_sincronizacion.log', '.log', 98000, 'LOG', 1);

PRINT 'Insertados 15 documentos de ejemplo';
GO

-- ============================================================================
-- 4. VERIFICACIÓN DE DATOS
-- ============================================================================

PRINT '';
PRINT '=== VERIFICACIÓN DE DATOS INSERTADOS ===';
PRINT '';

PRINT 'Total de Áreas:';
SELECT COUNT(*) FROM [dbo].[Areas];

PRINT 'Total de Incidencias:';
SELECT COUNT(*) FROM [dbo].[Incidences];

PRINT 'Total de Documentos:';
SELECT COUNT(*) FROM [dbo].[Documents];

PRINT '';
PRINT 'Muestra de datos:';
SELECT 
    a.[AreaCode],
    i.[IncidenceNumber],
    i.[IncidenceDate],
    d.[DocumentPath],
    d.[FileName]
FROM [dbo].[Incidences] i
INNER JOIN [dbo].[Areas] a ON i.[AreaId] = a.[AreaId]
LEFT JOIN [dbo].[Documents] d ON i.[IncidenceId] = d.[IncidenceId]
ORDER BY a.[AreaCode], i.[IncidenceNumber]
LIMIT 10;
GO
