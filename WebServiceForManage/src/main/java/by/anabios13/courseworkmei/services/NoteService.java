package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.dto.NoteDTO;
import by.anabios13.courseworkmei.dto.ResponsesDTO.Message;
import by.anabios13.courseworkmei.models.Note;
import by.anabios13.courseworkmei.models.User;
import by.anabios13.courseworkmei.repositories.INoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
//@Transactional(readOnly = true)
public class NoteService {
    private final INoteRepository INoteRepository;
    private final UserService userService;

    private final int notesPerPage = 10;//amount of directories in the page


    public NoteService(INoteRepository directoryRepository, UserService userService) {
        this.INoteRepository = directoryRepository;
        this.userService = userService;
    }


    //
    public List<Note> findAll(Integer page) {
        return INoteRepository.findAll(PageRequest.of(page, notesPerPage, Sort.by("accountCreationTime").descending())).getContent();
    }

    @Transactional
    public ResponseEntity<Map<String,Integer>> createNote(NoteDTO noteDTO,int userID){
    Note note = new Note();
    ModelMapper modelMapper= new ModelMapper();
        modelMapper.map(noteDTO,note);
        save(note,userID);
        note=findOne(note.getNoteId());
//        modelMapper.map(findOne(note.getNoteId()),noteDTO);
//        noteDTO.setNoteText(null);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("noteId: ",note.getNoteId()));
}

    @Transactional
    public ResponseEntity<Message> updateNote(NoteDTO noteDTO){
        Note note = findOne(noteDTO.getNoteId());
        if(note==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new Message("Note does not exist")
            );
        note.setNoteText(noteDTO.getNoteText());
        update(noteDTO.getNoteId(), note);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    public List<Directory> searchByName(String partOfNameTheBlank){
//        return directoryService.findByNameOfCarBlankContaining(partOfNameTheBlank);
//    }

    public Note findOne(int id) {
        Optional<Note> foundNote = INoteRepository.findById(id);
        return foundNote.orElse(null);
    }

    //Method for saving blank by authorisation user
    @Transactional
    public void save(Note note, Integer id) {
        note.setTimeOfNoteCreation(new Date());
        note.setNoteOwner(userService.findOne(id));
        INoteRepository.save(note);
    }

    public List<Note> searchNoteByNoteOwner(User noteOwner) {
        return INoteRepository.findByNoteOwner(noteOwner);
    }

    @Transactional
    public void update(int id, Note updatedNote) {
        updatedNote.setNoteId(id);
        INoteRepository.save(updatedNote);
    }

    @Transactional
    public void delete(int id) throws IllegalArgumentException{
        INoteRepository.deleteById(id);
    }
}
