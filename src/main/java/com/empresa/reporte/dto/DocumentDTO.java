package com.empresa.reporte.dto;

import java.util.Objects;

/**
 * DocumentDTO
 * Data Transfer Object para representar un Documento
 */
public class DocumentDTO {
    private Long documentId;
    private Long incidenceId;
    private String documentPath;
    private String fileName;
    private String fileExtension;
    private Long fileSize;
    private String documentType;
    private boolean exists;

    // Constructores
    public DocumentDTO() {}

    public DocumentDTO(Long documentId, String documentPath, String fileName) {
        this.documentId = documentId;
        this.documentPath = documentPath;
        this.fileName = fileName;
    }

    public DocumentDTO(Long documentId, Long incidenceId, String documentPath, 
                      String fileName, String fileExtension, Long fileSize, String documentType) {
        this.documentId = documentId;
        this.incidenceId = incidenceId;
        this.documentPath = documentPath;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.documentType = documentType;
    }

    // Getters y Setters
    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getIncidenceId() {
        return incidenceId;
    }

    public void setIncidenceId(Long incidenceId) {
        this.incidenceId = incidenceId;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDTO that = (DocumentDTO) o;
        return Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId);
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "documentId=" + documentId +
                ", incidenceId=" + incidenceId +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", exists=" + exists +
                '}';
    }
}
