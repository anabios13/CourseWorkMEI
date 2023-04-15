package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.models.Note;
import by.anabios13.courseworkmei.models.Person;
import by.anabios13.courseworkmei.repositories.NoteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NoteService {
    private final NoteRepository noteRepository;
    private final PeopleService peopleService;

    private final int notesPerPage = 10;//amount of directories in the page


    public NoteService(NoteRepository directoryRepository, PeopleService peopleService) {
        this.noteRepository = directoryRepository;
        this.peopleService = peopleService;
    }


    //
    public List<Note> findAll(Integer page) {
        return noteRepository.findAll(PageRequest.of(page, notesPerPage, Sort.by("accountCreationTime").descending())).getContent();
    }

//    public List<Directory> searchByName(String partOfNameTheBlank){
//        return directoryService.findByNameOfCarBlankContaining(partOfNameTheBlank);
//    }

    public Note findOne(int id) {
        Optional<Note> foundNote = noteRepository.findById(id);
        return foundNote.orElse(null);
    }

    //Method for saving blank by authorisation user
    @Transactional
    public void save(Note note, Integer id) {
        note.setNoteOwner(peopleService.findOne(id));//
        noteRepository.save(note);
    }

    public List<Note> searchNoteByNoteOwner(Person noteOwner) {
        return noteRepository.findByNoteOwner(noteOwner);
    }

    @Transactional
    public void update(int id, Note updatedNote) {
        updatedNote.setNoteId(id);
        noteRepository.save(updatedNote);
    }

    @Transactional
    public void delete(int id) {
        noteRepository.deleteById(id);
    }
}
