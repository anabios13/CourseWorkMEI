package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import jakarta.validation.constraints.NotEmpty;

public class DirectoryDTO {
    DirectoryDTO(){}

    private int directoryId;

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    @NotEmpty
    private String directoryName;


    private Short directoryIsVisible;


    private Short directoryIsFavorite;

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

    public DirectoryDTO(Directory directory) {
        this.directoryName = directory.getDirectoryName();
        this.directoryIsVisible = directory.getDirectoryIsVisible();
        this.directoryIsFavorite = directory.getDirectoryIsFavorite();
    }
}
