package com.mediscreen.notes.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;


@Document(collection = "Notes")
public class Notes {

    @Transient
    public static final String SEQUENCE_NAME = "notes_sequence";

    @Id
    private String id;

    private Integer patientId;

    private String practicionerNotes;

    public Notes() {
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
}
