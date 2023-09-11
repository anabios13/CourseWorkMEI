package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileDTO {
    // private UserDTO userDTO;
    private String userName;
    private byte[] userAvatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    private List<Directory> directories;

    private String userRole;
    private List<NoteDTO> noteDTOList = new ArrayList<>();
    private List<DirectoryDTO> directoryDTOList = new ArrayList<>();

    public ProfileDTO(User user) {
        this.userName = user.getUserName();
        this.userAvatar = user.getUserAvatar();
        this.userRole = user.getUserRole();

        for (int i = 0; i < user.getNotes().size(); i++) {
            this.noteDTOList.add(new NoteDTO(user.getNotes().get(i)));
        }
        for (int i = 0; i < user.getDirectories().size(); i++) {
            this.directoryDTOList.add(new DirectoryDTO(user.getDirectories().get(i)));
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
