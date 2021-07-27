package com.mediscreen.notes.TU;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.notes.DTO.NotesDTO;
import com.mediscreen.notes.domain.Notes;
import com.mediscreen.notes.repositories.NotesRepository;
import com.mediscreen.notes.services.NotesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotesService notesService;

    @MockBean
    private NotesRepository notesRepository;

    private Notes notes1 = new Notes() ;
    private Notes notes2= new Notes() ;

    private NotesDTO notesDTO1 = new NotesDTO() ;
    private NotesDTO notesDTO2= new NotesDTO() ;

    private List<NotesDTO> notesDTOList = new ArrayList<>();

    @BeforeEach
    public void setup(){

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dateCreation = LocalDate.now();
        LocalDate dateModification = LocalDate.now();

        notes1.setId("1");
        notes1.setPatientId(1);
        notes1.setPracticionerNotes("Test10");
        notes1.setDateCreation(dateCreation);
        notes1.setDateModification(dateModification);

        notes2.setId("2");
        notes2.setPatientId(1);
        notes2.setPracticionerNotes("Test11");
        notes2.setDateCreation(dateCreation);
        notes2.setDateModification(dateModification);



        notesDTO1.setId("1");
        notesDTO1.setPatientId(1);
        notesDTO1.setPracticionerNotes("Test10");
        notesDTO1.setDateCreation(dateCreation);
        notesDTO1.setDateModification(dateModification);

        notesDTO2.setId("2");
        notesDTO2.setPatientId(1);
        notesDTO2.setPracticionerNotes("Test11");
        notesDTO2.setDateCreation(dateCreation);
        notesDTO2.setDateModification(dateModification);


    }


    @Test
    public void getNotesById() throws Exception {



        when(notesRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(notes1));
        when(notesService.getNoteById(any())).thenReturn(notesDTO1);

        mockMvc.perform(get("/getNote/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void getNotesByIdNotFound() throws Exception {



        when(notesRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(null));
        when(notesService.getNoteById(any())).thenReturn(notesDTO1);

        mockMvc.perform(get("/getNote/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getNotesByPatientId() throws Exception {

        notesDTOList.add(notesDTO1);
        notesDTOList.add(notesDTO2);

        when(notesService.getAllNotesByPatientId(any())).thenReturn(notesDTOList);

        mockMvc.perform(get("/getNotesByPatient/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void addNotes() throws Exception {


        when(notesService.addNotes(any())).thenReturn(notesDTO1);

        mockMvc.perform((post("/addNotes"))
                .content(asJsonString(notesDTO1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    @Test
    public void updateNotes() throws Exception {



        when(notesRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(notes1));
        when(notesService.updateNotes(any())).thenReturn(notesDTO1);

        mockMvc.perform((post("/updateNotes"))
                .content(asJsonString(notesDTO1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

    }

    @Test
    public void updateNotesNotFound() throws Exception {



        when(notesRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(null));
        when(notesService.updateNotes(any())).thenReturn(notesDTO1);

        mockMvc.perform((post("/updateNotes"))
                .content(asJsonString(notesDTO1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deleteNotes() throws Exception {



        when(notesRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(notes1));
        when(notesService.deleteNotes(any())).thenReturn(notesDTO1);

        mockMvc.perform((get("/deleteNotes/1"))
                .content(asJsonString(notesDTO1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteNotesNotFound() throws Exception {



        when(notesRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(null));
        when(notesService.deleteNotes(any())).thenReturn(notesDTO1);

        mockMvc.perform((get("/deleteNotes/1"))
                .content(asJsonString(notesDTO1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }




    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
