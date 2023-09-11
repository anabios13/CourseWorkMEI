package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.models.Project;
import by.anabios13.courseworkmei.repositories.IProjectRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProjectService {
    private final IProjectRepository IProjectRepository;
    private final UserService userService;
    private final DirectoryService directoryService;

    private final int projectsPerPage = 10;//amount of directories in the page

    public ProjectService(IProjectRepository IProjectRepository, UserService userService, DirectoryService directoryService) {
        this.IProjectRepository = IProjectRepository;
        this.userService = userService;
        this.directoryService = directoryService;
    }


    //
    public List<Project> findAll(Integer page) {
        return IProjectRepository.findAll(PageRequest.of(page, projectsPerPage, Sort.by("timeOfProjectCreation").descending())).getContent();
    }

//    public List<Directory> searchByName(String partOfNameTheBlank){
//        return directoryService.findByNameOfCarBlankContaining(partOfNameTheBlank);
//    }

    public Project findOne(int id) {
        Optional<Project> foundProject = IProjectRepository.findById(id);
        return foundProject.orElse(null);
    }

    @Transactional
    public void save(Project project, Integer personId,Integer directoryId) {
        project.setProjectOwner(userService.findOne(personId));//
        project.setProjectDirectory(directoryService.findOne(directoryId));
        project.setTimeOfProjectCreation(new Date());
        IProjectRepository.save(project);
    }

    @Transactional
    public void update(int id, Project updatedProject) {
        updatedProject.setProjectId(id);
        IProjectRepository.save(updatedProject);
    }

    @Transactional
    public void delete(int id) {
        IProjectRepository.deleteById(id);
    }
}
