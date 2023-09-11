package by.anabios13.courseworkmei.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User noteOwner;

    @Size(min = 1, max = 500, message = "Note should be between 1 and 500 characters")
    @NotEmpty(message = "note should not be empty")
    @Column(name = "note_text")
    private String noteText;

    @Column(name = "time_of_note_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfNoteCreation;

    public Note() {
    }

    public Note(User noteOwner, String noteText) {
        this.noteOwner = noteOwner;
        this.noteText = noteText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(noteOwner, note.noteOwner) && Objects.equals(noteText, note.noteText) && Objects.equals(timeOfNoteCreation, note.timeOfNoteCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteOwner, noteText, timeOfNoteCreation);
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public User getNoteOwner() {
        return noteOwner;
    }

    public void setNoteOwner(User noteOwner) {
        this.noteOwner = noteOwner;
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

    public void setTimeOfNoteCreation(Date accountCreationTime) {
        this.timeOfNoteCreation = accountCreationTime;
    }

}
