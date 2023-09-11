package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {
    UserDTO(){}
    @Size(min = 1, max = 50, message = "Email should be between 1 and 50 characters")
    @NotEmpty(message = "name of user should not be empty")
    private String userName;
    @Size(min = 1, max = 255, message = "Email should be between 1 and 255 characters")
    @NotEmpty(message = "name of user should not be empty")
    private String userEmail;
    private byte[] userAvatar;
    private List<Directory> directories;

    private String userRole;

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userAvatar = user.getUserAvatar();
     //   this.directories = user.getDirectories();
       // this.notes = user.getNotes();
        this.userRole = user.getUserRole();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
