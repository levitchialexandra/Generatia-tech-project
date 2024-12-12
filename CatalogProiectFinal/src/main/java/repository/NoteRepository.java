package repository;


import org.springframework.data.repository.CrudRepository;

import basic.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {

}
