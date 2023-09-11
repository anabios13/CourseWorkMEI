package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Note;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class NoteDTO {

    private String noteText;
    private int noteId;
    public NoteDTO() {}
    public NoteDTO(String noteText) {
        this.noteText = noteText;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public NoteDTO(Note note) {
        this.noteText = note.getNoteText();
        this.timeOfNoteCreation = note.getTimeOfNoteCreation();
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Date getTimeOfNoteCreation() {
        return timeOfNoteCreation;
    }

    public void setTimeOfNoteCreation(Date timeOfNoteCreation) {
        this.timeOfNoteCreation = timeOfNoteCreation;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfNoteCreation;
}
