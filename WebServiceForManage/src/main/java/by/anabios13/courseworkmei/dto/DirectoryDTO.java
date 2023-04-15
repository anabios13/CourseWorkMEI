package by.anabios13.courseworkmei.dto;

import by.anabios13.courseworkmei.models.Directory;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class DirectoryDTO {
    @NotEmpty
    @Column(name = "directory_name")
    private String directoryName;

    @Column(name = "directory_is_visible")
    private Boolean directoryIsVisible;

    @Column(name = "directory_is_favorite")
    private Boolean directoryIsFavorite;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public Boolean getDirectoryIsVisible() {
        return directoryIsVisible;
    }

    public void setDirectoryIsVisible(Boolean directoryIsVisible) {
        this.directoryIsVisible = directoryIsVisible;
    }

    public Boolean getDirectoryIsFavorite() {
        return directoryIsFavorite;
    }

    public void setDirectoryIsFavorite(Boolean directoryIsFavorite) {
        this.directoryIsFavorite = directoryIsFavorite;
    }

    public DirectoryDTO(Directory directory) {
        this.directoryName = directory.getDirectoryName();
        this.directoryIsVisible = directory.getDirectoryIsVisible();
        this.directoryIsFavorite = directory.getDirectoryIsFavorite();
    }
}
