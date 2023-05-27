package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Note;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class NoteDTO {
    @Size(min = 1, max = 500, message = "Note should be between 1 and 500 characters")
    @NotEmpty(message = "note should not be empty")
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
