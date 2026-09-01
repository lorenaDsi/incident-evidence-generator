package com.empresa.reporte.exception;

/**
 * DatabaseException
 * Excepción para errores relacionados con la base de datos.
 */
public class DatabaseException extends IncidenceProcessingException {
    private static final long serialVersionUID = 1L;

    public DatabaseException(String message) {
        super("DATABASE_ERROR", message);
    }

    public DatabaseException(String message, Throwable cause) {
        super("DATABASE_ERROR", message, cause);
    }

    public DatabaseException(String message, int retryAttempts) {
        super("DATABASE_ERROR", message, retryAttempts);
    }

    public DatabaseException(String message, Throwable cause, int retryAttempts) {
        super("DATABASE_ERROR", message + " (Retry attempt: " + retryAttempts + ")", cause);
    }
}
