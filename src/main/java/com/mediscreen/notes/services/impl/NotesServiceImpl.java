package com.mediscreen.notes.services.impl;

import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.domain.Notes;
import com.mediscreen.notes.repositories.NotesRepository;
import com.mediscreen.notes.services.NotesService;
import com.mediscreen.notes.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    @Override
    public NotesDTO getNoteById(String id) {


        Notes notes = new Notes();

        NotesDTO notesDTO = new NotesDTO();

        notes = notesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));


        notesDTO.setId(notes.getId());
        notesDTO.setPatientId(notes.getPatientId());
        notesDTO.setPracticionerNotes(notes.getPracticionerNotes());

        return notesDTO;

    }

    public List<NotesDTO> getAllNotesByPatientId(Integer id){

        List<NotesDTO> notesDTOList = new ArrayList<>();
        List<Notes> notesList = new ArrayList<>();

        notesList = notesRepository.findAllByPatientId(id);
        for (Notes notesL : notesList) {

            NotesDTO notesDTO = new NotesDTO();
            notesDTO.setId(notesL.getId());
            notesDTO.setPatientId(notesL.getPatientId());
            notesDTO.setPracticionerNotes(notesL.getPracticionerNotes());

            notesDTOList.add(notesDTO);

        }

        return notesDTOList;

    }

    @Override
    public NotesDTO addNotes(NotesDTO notesDTO) {

        Notes notes = new Notes();

        Long seqValue = sequenceGeneratorService.generateSequence(Notes.SEQUENCE_NAME);

        notes.setId(seqValue.toString());
        notes.setPatientId(notesDTO.getPatientId());
        notes.setPracticionerNotes(notesDTO.getPracticionerNotes());

        notesRepository.save(notes);

        return notesDTO;
    }

    @Override
    public NotesDTO updateNotes(NotesDTO notesDTO) {

        Notes notes = new Notes();

        notes.setId(notesDTO.getId());
        notes.setPatientId(notesDTO.getPatientId());
        notes.setPracticionerNotes(notesDTO.getPracticionerNotes());

        notesRepository.save(notes);

        return notesDTO;
    }

    @Override
    public void deleteNotes(String id) {

        notesRepository.deleteById(id);
    }
}
