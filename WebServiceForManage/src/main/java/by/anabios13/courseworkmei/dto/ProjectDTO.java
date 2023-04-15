package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class ProjectDTO {

    private Person projectOwner;


    private Directory projectDirectory;

    @Size(min = 1, max = 50, message = "name of project should be between 1 and 50 characters")
    @NotEmpty(message = "name of project should not be empty")
    private String projectName;


    private Boolean projectIsVisible;


    private Boolean projectIsFavorite;


    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfProjectCreation;

    @Size(min = 1, max = 50, message = "project category should be between 1 and 50 characters")
    @NotEmpty(message = "project category should not be empty")
    private String projectCategory;

    public Person getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Person projectOwner) {
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

    public Boolean getProjectIsVisible() {
        return projectIsVisible;
    }

    public void setProjectIsVisible(Boolean projectIsVisible) {
        this.projectIsVisible = projectIsVisible;
    }

    public Boolean getProjectIsFavorite() {
        return projectIsFavorite;
    }

    public void setProjectIsFavorite(Boolean projectIsFavorite) {
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
}
