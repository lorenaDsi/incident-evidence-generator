package com.empresa.reporte.exception;

/**
 * ValidationException
 * Excepción para errores de validación de entrada.
 */
public class ValidationException extends IncidenceProcessingException {
    private static final long serialVersionUID = 1L;
    private final String fieldName;

    public ValidationException(String message) {
        super("VALIDATION_ERROR", message);
        this.fieldName = null;
    }

    public ValidationException(String fieldName, String message) {
        super("VALIDATION_ERROR", "[" + fieldName + "] " + message);
        this.fieldName = fieldName;
    }

    public ValidationException(String fieldName, String message, Throwable cause) {
        super("VALIDATION_ERROR", "[" + fieldName + "] " + message, cause);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
