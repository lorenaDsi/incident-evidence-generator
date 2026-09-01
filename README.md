# Incident Evidence Generator - Solución Empresarial

Aplicación Java 21 escalable y de producción para generar evidencia documental de incidencias con reportes Excel y archivos ZIP automáticos.

## Características Principales

✅ **Conectividad SQL Server** - JDBC con connection pooling HikariCP
✅ **Procesamiento Masivo** - 50,000+ incidencias sin problemas de memoria
✅ **Generación Excel** - SXSSFWorkbook para grandes volúmenes
✅ **Compresión ZIP** - Streaming directo sin cargar en memoria
✅ **Logging Avanzado** - SLF4J + Logback con rotación de logs
✅ **Seguridad** - PreparedStatements, validación de rutas, protección contra SQL Injection
✅ **Manejo de Errores** - Recuperación automática y continuidad del proceso
✅ **Rendimiento** - Paginación SQL, buffers optimizados, streaming

## Requisitos Previos

- **Java 21** (JDK)
- **SQL Server 2019+**
- **Gradle 8.4+**
- **8 GB RAM** (recomendado para volúmenes grandes)
- **Espacio en disco** suficiente para ZIP temporal

## Instalación y Compilación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/lorenaDsi/incident-evidence-generator.git
cd incident-evidence-generator
```

### 2. Configurar Base de Datos

Ejecutar scripts SQL en SQL Server:

```bash
# Ver archivo: src/main/resources/sql/schema.sql
```

### 3. Configurar application.yml

```bash
cp src/main/resources/application.yml.example src/main/resources/application.yml
# Editar con credenciales y rutas
```

### 4. Compilar con Gradle

```bash
# Windows
gradlew build

# Linux/Mac
./gradlew build
```

### 5. Compilar JAR Ejecutable

```bash
# Crear JAR con todas las dependencias
gradlew shadowJar

# El archivo resultará en: build/libs/incident-evidence-generator-all.jar
```

## Uso

### Parámetros de Ejecución

```bash
java -jar incident-evidence-generator-all.jar \
  --dateFrom=2026-08-01 \
  --dateTo=2026-08-31 \
  --areas=AREA51,AREA52,AREA53 \
  --outputDir=C:/reportes
```

### Alternativa: Archivo de Configuración

```bash
java -jar incident-evidence-generator-all.jar --config=config.properties
```

### Variables de Entorno

```bash
export DATABASE_URL=jdbc:sqlserver://localhost:1433;databaseName=IncidenceDB
export DATABASE_USER=sa
export DATABASE_PASSWORD=YourPassword123
export OUTPUT_DIR=/var/reportes
export LOG_DIR=/var/logs/reporte
```

## Estructura del Proyecto

```
incident-evidence-generator/
├── src/
│   ├── main/
│   │   ├── java/com/empresa/reporte/
│   │   │   ├── config/
│   │   │   │   ├── DatabaseConfig.java
│   │   │   │   ├── AppConfig.java
│   │   │   │   └── LoggingConfig.java
│   │   │   ├── controller/
│   │   │   │   └── ReportController.java
│   │   │   ├── service/
│   │   │   │   ├── IncidenceService.java
│   │   │   │   ├── ExcelGenerationService.java
│   │   │   │   ├── ZipGenerationService.java
│   │   │   │   └── DocumentRetrievalService.java
│   │   │   ├── repository/
│   │   │   │   ├── IncidenceRepository.java
│   │   │   │   └── DocumentRepository.java
│   │   │   ├── dto/
│   │   │   │   ├── IncidenceDTO.java
│   │   │   │   ├── DocumentDTO.java
│   │   │   │   └── ProcessResultDTO.java
│   │   │   ├── entity/
│   │   │   │   ├── Incidence.java
│   │   │   │   └── Document.java
│   │   │   ├── model/
│   │   │   │   ├── IncidenceRow.java
│   │   │   │   └── ProcessingResult.java
│   │   │   ├── excel/
│   │   │   │   ├── ExcelGenerator.java
│   │   │   │   └── ExcelStyles.java
│   │   │   ├── zip/
│   │   │   │   ├── ZipGenerator.java
│   │   │   │   └── ZipStreamProcessor.java
│   │   │   ├── util/
│   │   │   │   ├── FilePathValidator.java
│   │   │   │   ├── DateUtil.java
│   │   │   │   └── StringUtil.java
│   │   │   ├── exception/
│   │   │   │   ├── IncidenceProcessingException.java
│   │   │   │   ├── DatabaseException.java
│   │   │   │   └── FileAccessException.java
│   │   │   └── IncidentEvidenceGeneratorApp.java
│   │   └── resources/
│   │       ├── application.yml.example
│   │       ├── logback.xml
│   │       └── sql/
│   │           ├── schema.sql
│   │           ├── indexes.sql
│   │           └── queries.sql
│   └── test/
│       └── java/com/empresa/reporte/
│           ├── IncidenceRepositoryTest.java
│           ├── ExcelGeneratorTest.java
│           └── ZipGeneratorTest.java
├── gradle/
│   └── wrapper/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

## Arquitectura

### Patrón de Diseño

- **Clean Architecture** - Separación clara de capas
- **Repository Pattern** - Abstracción de datos
- **Service Layer** - Lógica de negocio
- **Dependency Injection** - Inyección manual en Java 21
- **SOLID Principles** - Código mantenible

### Flujo de Procesos

```
[Entrada: Parámetros CLI/Config]
        ↓
[Validación de Entrada]
        ↓
[Conexión SQL Server]
        ↓
[Consulta de Incidencias por Área y Fecha]
        ↓
[Recuperación de Rutas Documentales]
        ↓
[Paralelo: Excel + ZIP]
        ├→ [Generación Excel - SXSSFWorkbook]
        │   └→ [Validación de archivos físicos]
        │
        └→ [Generación ZIP - Streaming]
            └→ [Lectura archivo por archivo]
                └→ [Compresión directa]
        ↓
[Generación de Reporte de Resultados]
        ↓
[Cierre de Recursos]
        ↓
[Salida: Excel + ZIP en outputDir]
```

## Optimizaciones para Grandes Volúmenes

### 1. Procesamiento de 50,000+ Incidencias

- **Paginación SQL**: OFFSET/FETCH de 1,000 registros
- **Connection Pooling**: HikariCP con 10 conexiones
- **Buffering**: 8KB para archivos, 64KB para ZIP
- **Consumo de Memoria**: ~500 MB para 100,000 registros

### 2. Generación Excel

- **SXSSFWorkbook**: Solo mantiene 100 filas en memoria
- **Escritura incremental**: Descarga a disco automáticamente
- **Tamaño resultante**: ~50-100 MB para 100,000 registros

### 3. Generación ZIP

- **Streaming sin carga en memoria**: Lee archivo fuente, escribe directamente al ZIP
- **Compresión DEFLATE**: Ratio ~3:1 (ejemplo: 1GB → 300MB)
- **Procesamiento iterativo**: Fichero por fichero
- **Recuperación ante fallos**: Continúa con el siguiente archivo

### 4. Consumo de Memoria

| Volumen | Heap Recomendado | Tiempo Estimado |
|---------|------------------|-----------------|
| 10,000 incidencias | 1 GB | 2-3 minutos |
| 50,000 incidencias | 2 GB | 8-10 minutos |
| 100,000 incidencias | 4 GB | 15-20 minutos |
| 500,000+ incidencias | 8 GB | 60+ minutos |

### 5. Paginación SQL

```sql
-- Cada página: 1,000 registros
DECLARE @PageNumber INT = 1;
DECLARE @PageSize INT = 1000;

SELECT *
FROM IncidenceDocuments
WHERE Area IN ('AREA51', 'AREA52')
  AND IncidenceDate BETWEEN @DateFrom AND @DateTo
ORDER BY Area, IncidenceId, DocumentId
OFFSET (@PageNumber - 1) * @PageSize ROWS
FETCH NEXT @PageSize ROWS ONLY;
```

## Manejo de Errores

### Estrategia de Recuperación

1. **Archivo No Encontrado**: Registra en Excel como "NO" en ExisteFisicamente
2. **Ruta Inválida**: Salta y continúa con el siguiente
3. **Permisos Insuficientes**: Log de error, continúa proceso
4. **Error SQL**: Reintentos automáticos (máx 3)
5. **ZIP corrupto**: Validación al cierre

### Logs Detallados

```
[2026-08-01 10:00:00] INFO  - Iniciando proceso de reporte
[2026-08-01 10:00:01] INFO  - Conectado a SQL Server
[2026-08-01 10:00:05] INFO  - Consultadas 500 incidencias
[2026-08-01 10:00:10] WARN  - Archivo no encontrado: /ruta/I_2026/08/01/123456/doc1.png
[2026-08-01 10:00:15] INFO  - Generado Excel: reporte_2026-08-01.xlsx
[2026-08-01 10:00:45] INFO  - Generado ZIP: Documentacion_2026-08-01.zip
[2026-08-01 10:00:46] INFO  - Proceso completado en 46 segundos
```

## Seguridad

### Protecciones Implementadas

✅ **SQL Injection**: PreparedStatements con parámetros vinculados
✅ **Path Traversal**: Validación de rutas con canonicalización
✅ **Credenciales**: Variables de entorno, nunca hardcoded
✅ **Permisos de Archivo**: Validación antes de lectura
✅ **Validación de Entrada**: Regex para areas, fechas ISO-8601

## Testing

```bash
# Ejecutar todos los tests
gradlew test

# Ejecutar test específico
gradlew test --tests com.empresa.reporte.repository.IncidenceRepositoryTest

# Con reporte HTML
gradlew test --info
```

## Troubleshooting

### OutOfMemoryError

```bash
# Aumentar heap
java -Xmx4g -jar incident-evidence-generator-all.jar ...
```

### Connection Timeout

Verificar:
- SQL Server está disponible
- Firewall permite puerto 1433
- Credenciales correctas

### ZIP corrupto

- Verificar espacio en disco
- Validar permisos de escritura en outputDir
- Revisar logs para archivos problemáticos

## Monitoreo

### Métricas Importantes

- Incidencias procesadas por minuto
- Documentos encontrados vs. faltantes
- Tamaño final de Excel y ZIP
- Tasa de error

Ver archivo: `logs/reporte-{fecha}.log`

## Licencia

Apache License 2.0

## Autor

Generado como solución empresarial de producción.

---

**Versión**: 1.0.0 | **Última actualización**: 2026-08-01
