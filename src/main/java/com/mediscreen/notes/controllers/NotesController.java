package com.mediscreen.notes.controllers;


import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.Exceptions.ControllerException;
import com.mediscreen.notes.domain.Notes;
import com.mediscreen.notes.repositories.NotesRepository;
import com.mediscreen.notes.services.NotesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Control;
import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {

    private static final Logger logger = LogManager.getLogger(NotesController.class);

    @Autowired
    private NotesService notesService;

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/")
    public String index(){return "MS Notes";}


    @GetMapping ("/getNote/{id}")
    public NotesDTO getNote(@PathVariable("id") String id) throws ControllerException {

        Optional<Notes> notes = notesRepository.findById(id);

        if (!notes.isPresent()) {
            logger.error("Note is not found");
            throw new ControllerException("Note is not found");
        }
        else {
            return notesService.getNoteById(id);
        }

    }

    @GetMapping ("/getNotesByPatient/{patientId}")
    public List<NotesDTO> getNotes(@PathVariable("patientId") Integer patientId) {

        return notesService.getAllNotesByPatientId(patientId);

    }

    @PostMapping("/addNotes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NotesDTO addNotes(@RequestBody NotesDTO notesDTO) {

        return notesService.addNotes(notesDTO);

    }

    @PostMapping("/updateNotes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NotesDTO updateNotes(@RequestBody NotesDTO notesDTO) throws ControllerException {

        Optional<Notes> notes = notesRepository.findById(notesDTO.getId());

        if (!notes.isPresent()) {
            logger.error("Note is not found");
            throw new ControllerException("Note is not found");
        }
        else {
            return notesService.updateNotes(notesDTO);
        }

    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable("id") String id) throws ControllerException {

        Optional<Notes> notes = notesRepository.findById(id);

        if (!notes.isPresent()) {
            logger.error("Note is not found");
            throw new ControllerException("Note is not found");
        }
        else {
            notesService.deleteNotes(id);
        }

        return "delete done";
    }
}
