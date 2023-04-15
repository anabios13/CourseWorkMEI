package by.anabios13.courseworkmei.repositories;

import by.anabios13.courseworkmei.models.Note;
import by.anabios13.courseworkmei.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Note> findByNoteOwner(Person noteOwner);
}
