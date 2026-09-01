package com.empresa.reporte.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ProcessResultDTO
 * DTO que contiene los resultados del procesamiento
 */
public class ProcessResultDTO {
    private String processId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private int totalIncidences;
    private int totalDocuments;
    private int documentsFound;
    private int documentsMissing;
    private long processingTimeMs;
    private String excelFileName;
    private String zipFileName;
    private String errorMessage;
    private boolean success;

    // Constructores
    public ProcessResultDTO() {
        this.startTime = LocalDateTime.now();
    }

    public ProcessResultDTO(String processId) {
        this.processId = processId;
        this.startTime = LocalDateTime.now();
    }

    // Getters y Setters
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public long getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessResultDTO that = (ProcessResultDTO) o;
        return Objects.equals(processId, that.processId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId);
    }

    @Override
    public String toString() {
        return "ProcessResultDTO{" +
                "processId='" + processId + '\'' +
                ", status='" + status + '\'' +
                ", totalIncidences=" + totalIncidences +
                ", totalDocuments=" + totalDocuments +
                ", documentsFound=" + documentsFound +
                ", documentsMissing=" + documentsMissing +
                ", processingTimeMs=" + processingTimeMs +
                ", success=" + success +
                '}';
    }
}
