package com.mediscreen.notes.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.time.LocalDate;


@Document(collection = "Notes")
public class Notes {

    @Transient
    public static final String SEQUENCE_NAME = "notes_sequence";

    @Id
    private String id;

    private Integer patientId;

    private String practicionerNotes;

    private LocalDate dateCreation;

    private LocalDate dateModification;

    public Notes() {
    }

    public Notes(String id, Integer patientId, String practicionerNotes, LocalDate dateCreation, LocalDate dateModification) {
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

    public void setPracticionerNotes(String practicianNotes) {
        this.practicionerNotes = practicianNotes;
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
