package com.empresa.reporte.exception;

/**
 * IncidenceProcessingException
 * Excepción base para errores en el procesamiento de incidencias.
 */
public class IncidenceProcessingException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final int retryAttempts;

    public IncidenceProcessingException(String message) {
        super(message);
        this.errorCode = "UNKNOWN";
        this.retryAttempts = 0;
    }

    public IncidenceProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "UNKNOWN";
        this.retryAttempts = 0;
    }

    public IncidenceProcessingException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.retryAttempts = 0;
    }

    public IncidenceProcessingException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.retryAttempts = 0;
    }

    public IncidenceProcessingException(String errorCode, String message, int retryAttempts) {
        super(message);
        this.errorCode = errorCode;
        this.retryAttempts = retryAttempts;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }
}
