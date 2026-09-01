package com.empresa.reporte.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * IncidenceDTO
 * Data Transfer Object para representar una Incidencia
 */
public class IncidenceDTO {
    private Long incidenceId;
    private String incidenceNumber;
    private String areaCode;
    private String areaName;
    private LocalDate incidenceDate;
    private LocalDateTime incidenceDateTime;
    private String status;
    private String description;
    private int documentCount;

    // Constructores
    public IncidenceDTO() {}

    public IncidenceDTO(Long incidenceId, String incidenceNumber, String areaCode, 
                        LocalDate incidenceDate, String status) {
        this.incidenceId = incidenceId;
        this.incidenceNumber = incidenceNumber;
        this.areaCode = areaCode;
        this.incidenceDate = incidenceDate;
        this.status = status;
    }

    public IncidenceDTO(Long incidenceId, String incidenceNumber, String areaCode, 
                        String areaName, LocalDate incidenceDate, LocalDateTime incidenceDateTime,
                        String status, String description) {
        this.incidenceId = incidenceId;
        this.incidenceNumber = incidenceNumber;
        this.areaCode = areaCode;
        this.areaName = areaName;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public int getDocumentCount() {
        return documentCount;
    }

    public void setDocumentCount(int documentCount) {
        this.documentCount = documentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidenceDTO that = (IncidenceDTO) o;
        return Objects.equals(incidenceId, that.incidenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidenceId);
    }

    @Override
    public String toString() {
        return "IncidenceDTO{" +
                "incidenceId=" + incidenceId +
                ", incidenceNumber='" + incidenceNumber + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", incidenceDate=" + incidenceDate +
                ", status='" + status + '\'' +
                ", documentCount=" + documentCount +
                '}';
    }
}
