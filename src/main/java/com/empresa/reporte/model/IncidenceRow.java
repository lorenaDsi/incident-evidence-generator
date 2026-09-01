package com.empresa.reporte.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * IncidenceRow
 * Modelo interno que representa una fila de incidencia con documento asociado
 */
public class IncidenceRow {
    private String areaCode;
    private String incidenceNumber;
    private LocalDate incidenceDate;
    private String documentPath;
    private String fileName;
    private boolean fileExists;

    // Constructores
    public IncidenceRow() {}

    public IncidenceRow(String areaCode, String incidenceNumber, LocalDate incidenceDate, 
                       String documentPath, String fileName) {
        this.areaCode = areaCode;
        this.incidenceNumber = incidenceNumber;
        this.incidenceDate = incidenceDate;
        this.documentPath = documentPath;
        this.fileName = fileName;
        this.fileExists = false;
    }

    public IncidenceRow(String areaCode, String incidenceNumber, LocalDate incidenceDate, 
                       String documentPath, String fileName, boolean fileExists) {
        this.areaCode = areaCode;
        this.incidenceNumber = incidenceNumber;
        this.incidenceDate = incidenceDate;
        this.documentPath = documentPath;
        this.fileName = fileName;
        this.fileExists = fileExists;
    }

    // Getters y Setters
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getIncidenceNumber() {
        return incidenceNumber;
    }

    public void setIncidenceNumber(String incidenceNumber) {
        this.incidenceNumber = incidenceNumber;
    }

    public LocalDate getIncidenceDate() {
        return incidenceDate;
    }

    public void setIncidenceDate(LocalDate incidenceDate) {
        this.incidenceDate = incidenceDate;
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

    public boolean isFileExists() {
        return fileExists;
    }

    public void setFileExists(boolean fileExists) {
        this.fileExists = fileExists;
    }

    public String getExistenceStatus() {
        return fileExists ? "SI" : "NO";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidenceRow that = (IncidenceRow) o;
        return Objects.equals(incidenceNumber, that.incidenceNumber) &&
               Objects.equals(documentPath, that.documentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidenceNumber, documentPath);
    }

    @Override
    public String toString() {
        return "IncidenceRow{" +
                "areaCode='" + areaCode + '\'' +
                ", incidenceNumber='" + incidenceNumber + '\'' +
                ", incidenceDate=" + incidenceDate +
                ", fileName='" + fileName + '\'' +
                ", fileExists=" + fileExists +
                '}';
    }
}
