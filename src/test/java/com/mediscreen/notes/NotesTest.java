package com.mediscreen.notes;


import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.domain.Notes;
import com.mediscreen.notes.repositories.NotesRepository;
import com.mediscreen.notes.services.NotesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NotesTest {


    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private NotesService notesService;

    private Notes notes10 = new Notes();
    private Notes notes11 = new Notes();
    private Notes notes20 = new Notes();
    private Notes notes21 = new Notes();

    private NotesDTO notesDTO10 = new NotesDTO();
    private NotesDTO notesDTO11 = new NotesDTO();
    private NotesDTO notesDTO20 = new NotesDTO();
    private NotesDTO notesDTO21 = new NotesDTO();

    @BeforeEach
    public void setup(){

        notesRepository.deleteAll();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dateCreation = LocalDate.now();
        LocalDate dateModification = LocalDate.now();

        notes10.setId("1");
        notes10.setPatientId(1);
        notes10.setPracticionerNotes("Test10");
        notes10.setDateCreation(dateCreation);
        notes10.setDateModification(dateModification);

        notes11.setId("2");
        notes11.setPatientId(1);
        notes11.setPracticionerNotes("Test11");
        notes11.setDateCreation(dateCreation);
        notes11.setDateModification(dateModification);

        notes20.setId("3");
        notes20.setPatientId(2);
        notes20.setPracticionerNotes("Test2011");
        notes20.setDateCreation(dateCreation);
        notes20.setDateModification(dateModification);

        notes21.setId("4");
        notes21.setPatientId(2);
        notes21.setPracticionerNotes("Test21");
        notes21.setDateCreation(dateCreation);
        notes21.setDateModification(dateModification);


        notesDTO10.setId("1");
        notesDTO10.setPatientId(1);
        notesDTO10.setPracticionerNotes("Test10");
        notesDTO10.setDateCreation(dateCreation);
        notesDTO10.setDateModification(dateModification);

        notesDTO11.setId("2");
        notesDTO11.setPatientId(1);
        notesDTO11.setPracticionerNotes("Test11");
        notesDTO11.setDateCreation(dateCreation);
        notesDTO11.setDateModification(dateModification);

        notesDTO20.setId("3");
        notesDTO20.setPatientId(2);
        notesDTO20.setPracticionerNotes("Test2011");
        notesDTO20.setDateCreation(dateCreation);
        notesDTO20.setDateModification(dateModification);

        notesDTO21.setId("4");
        notesDTO21.setPatientId(2);
        notesDTO21.setPracticionerNotes("Test21");
        notesDTO21.setDateCreation(dateCreation);
        notesDTO21.setDateModification(dateModification);

        notesRepository.save(notes10);
        notesRepository.save(notes11);
        notesRepository.save(notes20);
        notesRepository.save(notes21);

    }

    @Test
    public void getNoteById(){

        NotesDTO notesDTO = notesService.getNoteById("1");

        assertThat(notesDTO.getId()).isEqualTo("1");
        assertThat(notesDTO.getPatientId()).isEqualTo(1);
        assertThat(notesDTO.getPracticionerNotes()).isEqualTo("Test10");
    }

    @Test
    public void getAllNotesByPatientId() {

        List<NotesDTO> notesDTOList = notesService.getAllNotesByPatientId(1);

        assertThat(notesDTOList.size()).isEqualTo(2);
        assertThat(notesDTOList.get(0).getPracticionerNotes()).isEqualTo("Test10");
        assertThat(notesDTOList.get(1).getPracticionerNotes()).isEqualTo("Test11");


    }

    @Test
    public  void addNotesTest() {

        NotesDTO notesDTO = new NotesDTO(null,3,"Test30",null,null);

        notesService.addNotes(notesDTO);

        assertThat(notesRepository.findAllByPatientId(3).get(0).getPracticionerNotes()).isEqualTo("Test30");
        assertThat(notesRepository.findAllByPatientId(3).get(0).getDateCreation()).isNotNull();
        assertThat(notesRepository.findAllByPatientId(3).get(0).getDateModification()).isNotNull();

    }

    @Test
    public void updateNotesTest() {

        notesService.updateNotes(notesDTO20);

        assertThat(notesRepository.findById("3").get().getPracticionerNotes()).isEqualTo("Test2011");

    }
}
