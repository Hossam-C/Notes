package com.mediscreen.notes.DTO;

public class NotesDTO {


    private String id;
    private Integer patientId;
    private String practicionerNotes;

    public NotesDTO() {
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
}
