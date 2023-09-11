package by.anabios13.courseworkmei.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users",uniqueConstraints =
        {
                @UniqueConstraint(columnNames = "user_name"),
                @UniqueConstraint(columnNames = "user_email")
        })
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @OneToMany(mappedBy = "directoryOwner", cascade = CascadeType.PERSIST)
    private List<Directory> directories;

    @OneToMany(mappedBy = "noteOwner", cascade = CascadeType.PERSIST)
    private List<Note> notes;

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.PERSIST)
    private List<Project> projects;

    @Size(min = 1, max = 50, message = "Email should be between 1 and 50 characters")
    @NotEmpty(message = "name of user should not be empty")
    @Column(name = "user_name",unique = true)
    private String userName;
    @Size(min = 1, max = 255, message = "Email should be between 1 and 255 characters")
    @NotEmpty(message = "name of user should not be empty")
    @Column(name = "user_email",unique = true)
    private String userEmail;
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

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "user_avatar")
    private byte[] userAvatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userEmail, password);
    }

    public User(String userName, String userEmail, String password) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }
}