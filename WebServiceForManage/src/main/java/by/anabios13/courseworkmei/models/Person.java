package by.anabios13.courseworkmei.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person",uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "person_name"),
                @UniqueConstraint(columnNames = "person_email")
        })
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @OneToMany(mappedBy = "directoryOwner", cascade = CascadeType.PERSIST)
    private List<Directory> directories;

    @OneToMany(mappedBy = "noteOwner", cascade = CascadeType.PERSIST)
    private List<Note> notes;

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.PERSIST)
    private List<Project> projects;

    @Size(min = 1, max = 50, message = "Email should be between 1 and 50 characters")
    @NotEmpty(message = "name of person should not be empty")
    @Column(name = "person_name",unique = true)
    private String personName;
    @Size(min = 1, max = 255, message = "Email should be between 1 and 255 characters")
    @NotEmpty(message = "name of person should not be empty")
    @Column(name = "person_email",unique = true)
    private String personEmail;
    @Size(min = 1, max = 50, message = "Password should be between 1 and 50 characters")
    @NotEmpty(message = "password should not be empty")
    @Column(name = "password")
    private String password;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Column(name = "account_creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountCreationTime;

    @Column(name = "person_role")
    private String personRole;

    @Column(name = "person_avatar")
    private byte[] personAvatar;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAccountCreationTime() {
        return accountCreationTime;
    }

    public void setAccountCreationTime(Date accountCreationTime) {
        this.accountCreationTime = accountCreationTime;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    public byte[] getPersonAvatar() {
        return personAvatar;
    }

    public void setPersonAvatar(byte[] personAvatar) {
        this.personAvatar = personAvatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personName, person.personName) && Objects.equals(personEmail, person.personEmail) && Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, personEmail, password);
    }

    public Person(String personName, String personEmail, String password) {
        this.personName = personName;
        this.personEmail = personEmail;
        this.password = password;
    }

    public Person() {
    }
}