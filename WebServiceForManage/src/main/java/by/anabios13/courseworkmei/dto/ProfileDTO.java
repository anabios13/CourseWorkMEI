package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.Person;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {
    // private PersonDTO personDTO;
    private String personName;
    private byte[] personAvatar;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public byte[] getPersonAvatar() {
        return personAvatar;
    }

    public void setPersonAvatar(byte[] personAvatar) {
        this.personAvatar = personAvatar;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

    private List<Directory> directories;

    private String personRole;
    private List<NoteDTO> noteDTOList = new ArrayList<>();
    private List<DirectoryDTO> directoryDTOList = new ArrayList<>();

    public ProfileDTO(Person person) {
        this.personName = person.getPersonName();
        this.personAvatar = person.getPersonAvatar();
        this.personRole = person.getPersonRole();

        for (int i = 0; i < person.getNotes().size(); i++) {
            this.noteDTOList.add(new NoteDTO(person.getNotes().get(i)));
        }
        for (int i = 0; i < person.getDirectories().size(); i++) {
            this.directoryDTOList.add(new DirectoryDTO(person.getDirectories().get(i)));
        }
    }

    public List<NoteDTO> getNoteDTOList() {
        return noteDTOList;
    }

    public void setNoteDTOList(List<NoteDTO> noteDTOList) {
        this.noteDTOList = noteDTOList;
    }

    public List<DirectoryDTO> getDirectoryDTOList() {
        return directoryDTOList;
    }

    public void setDirectoryDTOList(List<DirectoryDTO> directoryDTOList) {
        this.directoryDTOList = directoryDTOList;
    }
}
