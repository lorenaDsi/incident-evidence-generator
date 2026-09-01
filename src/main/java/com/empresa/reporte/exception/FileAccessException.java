package com.empresa.reporte.exception;

/**
 * FileAccessException
 * Excepción para errores de acceso a archivos.
 */
public class FileAccessException extends IncidenceProcessingException {
    private static final long serialVersionUID = 1L;
    private final String filePath;

    public FileAccessException(String message) {
        super("FILE_ACCESS_ERROR", message);
        this.filePath = null;
    }

    public FileAccessException(String message, Throwable cause) {
        super("FILE_ACCESS_ERROR", message, cause);
        this.filePath = null;
    }

    public FileAccessException(String message, String filePath) {
        super("FILE_ACCESS_ERROR", message + " [File: " + filePath + "]");
        this.filePath = filePath;
    }

    public FileAccessException(String message, String filePath, Throwable cause) {
        super("FILE_ACCESS_ERROR", message + " [File: " + filePath + "]", cause);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
