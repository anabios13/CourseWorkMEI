package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Person;
import org.aspectj.weaver.ast.Not;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {
    private PersonDTO personDTO;
    private List<NoteDTO> noteDTOList = new ArrayList<>();
    private List<DirectoryDTO> directoryDTOList=new ArrayList<>();

    public ProfileDTO(Person person) {
        this.personDTO = new PersonDTO(person);

        for (int i = 0; i < person.getNotes().size(); i++) {
            this.noteDTOList.add(new NoteDTO(person.getNotes().get(i)));
        }
        for (int i = 0; i < person.getDirectories().size(); i++) {
            this.directoryDTOList.add(new DirectoryDTO(person.getDirectories().get(i)));
        }
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
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
