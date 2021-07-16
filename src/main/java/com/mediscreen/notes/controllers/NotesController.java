package com.mediscreen.notes.controllers;


import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping("/")
    public String index(){return "MS Notes";}


    @RequestMapping ("/getNote/{id}")
    public NotesDTO getNote(@PathVariable("id") String id) {

        return notesService.getNoteById(id);

    }

    @RequestMapping ("/getNotesByPatient/{patientId}")
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
    public NotesDTO updateNotes(@RequestBody NotesDTO notesDTO) {

        return notesService.updateNotes(notesDTO);

    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable("id") String id) {

        notesService.deleteNotes(id);

        return "delete done";
    }
}
