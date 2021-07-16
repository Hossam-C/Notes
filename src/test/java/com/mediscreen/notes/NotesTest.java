package com.mediscreen.notes;


import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.repositories.NotesRepository;
import com.mediscreen.notes.services.NotesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NotesTest {


    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesService notesService;

    @Test
    public void getAllNotesByPatientId() {

        List<NotesDTO> notesDTOList = notesService.getAllNotesByPatientId(99);

        for (NotesDTO notesDTO: notesDTOList) {

            System.out.println("Id: "+notesDTO.getId());
            System.out.println("PatientId: "+notesDTO.getPatientId());
            System.out.println("Notes: "+notesDTO.getPracticionerNotes());
        }


    }
}
