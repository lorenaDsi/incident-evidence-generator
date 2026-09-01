package com.empresa.reporte.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Incidence
 * Entidad JPA que representa una Incidencia
 */
public class Incidence {
    private Long incidenceId;
    private String incidenceNumber;
    private Long areaId;
    private LocalDate incidenceDate;
    private LocalDateTime incidenceDateTime;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Document> documents = new ArrayList<>();

    // Constructores
    public Incidence() {}

    public Incidence(String incidenceNumber, Long areaId, LocalDate incidenceDate) {
        this.incidenceNumber = incidenceNumber;
        this.areaId = areaId;
        this.incidenceDate = incidenceDate;
    }

    public Incidence(Long incidenceId, String incidenceNumber, Long areaId, 
                     LocalDate incidenceDate, LocalDateTime incidenceDateTime, 
                     String status, String description) {
        this.incidenceId = incidenceId;
        this.incidenceNumber = incidenceNumber;
        this.areaId = areaId;
        this.incidenceDate = incidenceDate;
        this.incidenceDateTime = incidenceDateTime;
        this.status = status;
        this.description = description;
    }

    // Getters y Setters
    public Long getIncidenceId() {
        return incidenceId;
    }

    public void setIncidenceId(Long incidenceId) {
        this.incidenceId = incidenceId;
    }

    public String getIncidenceNumber() {
        return incidenceNumber;
    }

    public void setIncidenceNumber(String incidenceNumber) {
        this.incidenceNumber = incidenceNumber;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public LocalDate getIncidenceDate() {
        return incidenceDate;
    }

    public void setIncidenceDate(LocalDate incidenceDate) {
        this.incidenceDate = incidenceDate;
    }

    public LocalDateTime getIncidenceDateTime() {
        return incidenceDateTime;
    }

    public void setIncidenceDateTime(LocalDateTime incidenceDateTime) {
        this.incidenceDateTime = incidenceDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void addDocument(Document document) {
        if (this.documents == null) {
            this.documents = new ArrayList<>();
        }
        this.documents.add(document);
        document.setIncidenceId(this.incidenceId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incidence that = (Incidence) o;
        return Objects.equals(incidenceId, that.incidenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidenceId);
    }

    @Override
    public String toString() {
        return "Incidence{" +
                "incidenceId=" + incidenceId +
                ", incidenceNumber='" + incidenceNumber + '\'' +
                ", areaId=" + areaId +
                ", incidenceDate=" + incidenceDate +
                ", status='" + status + '\'' +
                ", documentCount=" + (documents != null ? documents.size() : 0) +
                '}';
    }
}
