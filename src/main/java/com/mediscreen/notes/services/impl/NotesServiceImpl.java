package com.mediscreen.notes.services.impl;

import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.domain.Notes;
import com.mediscreen.notes.repositories.NotesRepository;
import com.mediscreen.notes.services.NotesService;
import com.mediscreen.notes.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        notesDTO.setDateCreation(notes.getDateCreation());
        notesDTO.setDateModification(notes.getDateModification());

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
            notesDTO.setDateCreation(notesL.getDateCreation());
            notesDTO.setDateModification(notesL.getDateModification());

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
        notes.setDateCreation(LocalDate.now());
        notes.setDateModification(LocalDate.now());

        notesRepository.save(notes);

        notesDTO.setId(notes.getId());
        notesDTO.setDateCreation(notes.getDateCreation());
        notesDTO.setDateModification(notes.getDateModification());

        return notesDTO;
    }

    @Override
    public NotesDTO updateNotes(NotesDTO notesDTO) {

        Notes notes = new Notes();

        notes.setId(notesDTO.getId());
        notes.setPatientId(notesDTO.getPatientId());
        notes.setPracticionerNotes(notesDTO.getPracticionerNotes());
        notes.setDateCreation(notesDTO.getDateCreation());
        notes.setDateModification(LocalDate.now());

        notesRepository.save(notes);

        notesDTO.setDateCreation(notes.getDateCreation());
        notesDTO.setDateModification(notes.getDateModification());

        return notesDTO;
    }

    @Override
    public NotesDTO deleteNotes(String id) {

        Optional<Notes> notes = notesRepository.findById(id);

        NotesDTO notesDTO = new NotesDTO(notes.get().getId(),notes.get().getPatientId(),notes.get().getPracticionerNotes(),notes.get().getDateCreation(),notes.get().getDateModification());

        notesRepository.deleteById(id);

        return notesDTO;
    }
}
