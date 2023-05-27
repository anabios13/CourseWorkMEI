package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.Person;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PersonDTO {
    PersonDTO(){}
    @Size(min = 1, max = 50, message = "Email should be between 1 and 50 characters")
    @NotEmpty(message = "name of person should not be empty")
    private String personName;
    @Size(min = 1, max = 255, message = "Email should be between 1 and 255 characters")
    @NotEmpty(message = "name of person should not be empty")
    private String personEmail;
    private byte[] personAvatar;
    private List<Directory> directories;

    private String personRole;

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    public PersonDTO(Person person) {
        this.personName = person.getPersonName();
        this.personEmail = person.getPersonEmail();
        this.personAvatar = person.getPersonAvatar();
     //   this.directories = person.getDirectories();
       // this.notes = person.getNotes();
        this.personRole = person.getPersonRole();
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

    public byte[] getPersonAvatar() {
        return personAvatar;
    }

    public void setPersonAvatar(byte[] personAvatar) {
        this.personAvatar = personAvatar;
    }

//    public List<Directory> getDirectories() {
//        return directories;
//    }

//    public void setDirectories(List<Directory> directories) {
//        this.directories = directories;
//    }
}
