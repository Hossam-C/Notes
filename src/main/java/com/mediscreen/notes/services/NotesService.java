package com.mediscreen.notes.services;

import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.domain.Notes;

import java.util.List;
import java.util.Optional;

public interface NotesService {

    public NotesDTO getNoteById(String id);

    public List<NotesDTO> getAllNotesByPatientId(Integer id);

    public NotesDTO addNotes(NotesDTO notesDTO);

    public NotesDTO updateNotes(NotesDTO notesDTO);

    public void deleteNotes(String id);
}
