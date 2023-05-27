package by.anabios13.courseworkmei.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "directory")
public class Directory {
    @Id
    @Column(name = "directory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int directoryId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person directoryOwner;

    @OneToMany(mappedBy = "projectDirectory", cascade = CascadeType.PERSIST)
    private List <Project> projects;

    @NotEmpty
    @Column(name = "directory_name")
    private String directoryName;

    @Column(name = "directory_is_visible")
    private Short directoryIsVisible;

    @Column(name = "directory_is_favorite")
    private Short directoryIsFavorite;

    public Directory() {
    }

    public Directory(Person owner, String directoryName, Short directoryIsVisible, Short directoryIsFavorite) {
        this.directoryOwner = owner;
        this.directoryName = directoryName;
        this.directoryIsVisible = directoryIsVisible;
        this.directoryIsFavorite = directoryIsFavorite;
    }

    public int getDirectoryId() {
        return directoryId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    public Person getDirectoryOwner() {
        return directoryOwner;
    }

    public void setDirectoryOwner(Person owner) {
        this.directoryOwner = owner;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public Short getDirectoryIsVisible() {
        return directoryIsVisible;
    }

    public void setDirectoryIsVisible(Short directoryIsVisible) {
        this.directoryIsVisible = directoryIsVisible;
    }

    public Short getDirectoryIsFavorite() {
        return directoryIsFavorite;
    }

    public void setDirectoryIsFavorite(Short directoryIsFavorite) {
        this.directoryIsFavorite = directoryIsFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(directoryOwner, directory.directoryOwner) && Objects.equals(directoryName, directory.directoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directoryOwner, directoryName);
    }
}
