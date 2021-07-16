package com.mediscreen.notes.repositories;

import com.mediscreen.notes.domain.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotesRepository extends MongoRepository<Notes, String> {


    public List<Notes> findAllByPatientId(Integer id);

    public Notes findNotesById(String id);

}
