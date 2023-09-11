package by.anabios13.courseworkmei.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User projectOwner;

    @ManyToOne
    @JoinColumn(name = "directory_id", referencedColumnName = "directory_id")
    private Directory projectDirectory;

    @Size(min = 1, max = 50, message = "name of project should be between 1 and 50 characters")
    @NotEmpty(message = "name of project should not be empty")
    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_is_visible")
    private Short projectIsVisible;

    @Column(name = "project_is_favorite")
    private Short projectIsFavorite;

    @Column(name = "time_of_project_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfProjectCreation;

    @Size(min = 1, max = 50, message = "project category should be between 1 and 50 characters")
    @NotEmpty(message = "project category should not be empty")
    @Column(name = "project_category")
    private String projectCategory;

    @Column(name = "project_container")
    private byte[] projectContainer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectOwner, project.projectOwner) && Objects.equals(projectDirectory, project.projectDirectory) && Objects.equals(projectName, project.projectName) && Objects.equals(timeOfProjectCreation, project.timeOfProjectCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectOwner, projectDirectory, projectName, timeOfProjectCreation);
    }

    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public User getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(User projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Directory getProjectDirectory() {
        return projectDirectory;
    }

    public void setProjectDirectory(Directory projectDirectory) {
        this.projectDirectory = projectDirectory;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Short getProjectIsVisible() {
        return projectIsVisible;
    }

    public void setProjectIsVisible(Short projectIsVisible) {
        this.projectIsVisible = projectIsVisible;
    }

    public Short getProjectIsFavorite() {
        return projectIsFavorite;
    }

    public void setProjectIsFavorite(Short projectIsFavorite) {
        this.projectIsFavorite = projectIsFavorite;
    }

    public Date getTimeOfProjectCreation() {
        return timeOfProjectCreation;
    }

    public void setTimeOfProjectCreation(Date timeOfProjectCreation) {
        this.timeOfProjectCreation = timeOfProjectCreation;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public byte[] getProjectContainer() {
        return projectContainer;
    }

    public void setProjectContainer(byte[] projectContainer) {
        this.projectContainer = projectContainer;
    }


}
