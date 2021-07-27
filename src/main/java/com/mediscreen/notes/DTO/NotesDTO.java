package com.mediscreen.notes.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class NotesDTO {


    private String id;
    private Integer patientId;
    private String practicionerNotes;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateCreation;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateModification;

    public NotesDTO() {
    }

    public NotesDTO(String id, Integer patientId, String practicionerNotes, LocalDate dateCreation, LocalDate dateModification) {
        this.id = id;
        this.patientId = patientId;
        this.practicionerNotes = practicionerNotes;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPracticionerNotes() {
        return practicionerNotes;
    }

    public void setPracticionerNotes(String practicionerNotes) {
        this.practicionerNotes = practicionerNotes;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }
}
