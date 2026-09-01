package com.empresa.reporte.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Document
 * Entidad JPA que representa un Documento
 */
public class Document {
    private Long documentId;
    private Long incidenceId;
    private String documentPath;
    private String fileName;
    private String fileExtension;
    private Long fileSize;
    private String documentType;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // Constructores
    public Document() {}

    public Document(String documentPath, String fileName) {
        this.documentPath = documentPath;
        this.fileName = fileName;
    }

    public Document(Long incidenceId, String documentPath, String fileName, 
                   String fileExtension, Long fileSize, String documentType) {
        this.incidenceId = incidenceId;
        this.documentPath = documentPath;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.documentType = documentType;
        this.isActive = true;
    }

    public Document(Long documentId, Long incidenceId, String documentPath, 
                   String fileName, String fileExtension, Long fileSize, 
                   String documentType, Boolean isActive) {
        this.documentId = documentId;
        this.incidenceId = incidenceId;
        this.documentPath = documentPath;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.documentType = documentType;
        this.isActive = isActive;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", incidenceId=" + incidenceId +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", isActive=" + isActive +
                '}';
    }
}
