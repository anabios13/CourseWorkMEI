package by.anabios13.courseworkmei.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class ProjectDTO {
    ProjectDTO() {
    }

    public int getProjectId() {
        return projectId;
    }
    private int projectOwnerId;
    private int projectDirectoryId;

    public int getProjectOwnerId() {
        return projectOwnerId;
    }

    public void setProjectOwnerId(int projectOwnerId) {
        this.projectOwnerId = projectOwnerId;
    }

    public int getProjectDirectoryId() {
        return projectDirectoryId;
    }

    public void setProjectDirectoryId(int projectDirectoryId) {
        this.projectDirectoryId = projectDirectoryId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private int projectId;


//    private Person projectOwner;
//
//
//    private Directory projectDirectory;

    @Size(min = 1, max = 50, message = "name of project should be between 1 and 50 characters")
    @NotEmpty(message = "name of project should not be empty")
    private String projectName;


    private Short projectIsVisible;


    private Short projectIsFavorite;


    private Date timeOfProjectCreation;


    private String projectCategory;

//    public Person getProjectOwner() {
//        return projectOwner;
//    }
//
//    public void setProjectOwner(Person projectOwner) {
//        this.projectOwner = projectOwner;
//    }
//
//    public Directory getProjectDirectory() {
//        return projectDirectory;
//    }
//
//    public void setProjectDirectory(Directory projectDirectory) {
//        this.projectDirectory = projectDirectory;
//    }

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
}
