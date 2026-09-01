package com.empresa.reporte.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ProcessingResult
 * Modelo que almacena los resultados del procesamiento
 */
public class ProcessingResult {
    private String processId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalIncidences;
    private int totalDocuments;
    private int documentsFound;
    private int documentsMissing;
    private String excelFilePath;
    private String zipFilePath;
    private List<String> errors;
    private List<String> warnings;
    private boolean successful;

    // Constructores
    public ProcessingResult(String processId) {
        this.processId = processId;
        this.startTime = LocalDateTime.now();
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.successful = true;
    }

    // Getters y Setters
    public String getProcessId() {
        return processId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getTotalIncidences() {
        return totalIncidences;
    }

    public void setTotalIncidences(int totalIncidences) {
        this.totalIncidences = totalIncidences;
    }

    public int getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(int totalDocuments) {
        this.totalDocuments = totalDocuments;
    }

    public int getDocumentsFound() {
        return documentsFound;
    }

    public void setDocumentsFound(int documentsFound) {
        this.documentsFound = documentsFound;
    }

    public int getDocumentsMissing() {
        return documentsMissing;
    }

    public void setDocumentsMissing(int documentsMissing) {
        this.documentsMissing = documentsMissing;
    }

    public String getExcelFilePath() {
        return excelFilePath;
    }

    public void setExcelFilePath(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public String getZipFilePath() {
        return zipFilePath;
    }

    public void setZipFilePath(String zipFilePath) {
        this.zipFilePath = zipFilePath;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
        this.successful = false;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warning) {
        this.warnings.add(warning);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public long getProcessingTimeMs() {
        if (startTime != null && endTime != null) {
            return java.time.temporal.ChronoUnit.MILLIS.between(startTime, endTime);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ProcessingResult{" +
                "processId='" + processId + '\'' +
                ", totalIncidences=" + totalIncidences +
                ", totalDocuments=" + totalDocuments +
                ", documentsFound=" + documentsFound +
                ", documentsMissing=" + documentsMissing +
                ", processingTimeMs=" + getProcessingTimeMs() +
                ", successful=" + successful +
                '}';
    }
}
