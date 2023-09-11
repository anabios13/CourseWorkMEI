package by.anabios13.courseworkmei.controllers;

import by.anabios13.courseworkmei.dto.DirectoryDTO;
import by.anabios13.courseworkmei.dto.NoteDTO;
import by.anabios13.courseworkmei.dto.ProfileDTO;
import by.anabios13.courseworkmei.dto.ProjectDTO;
import by.anabios13.courseworkmei.dto.ResponsesDTO.Message;
import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.Project;
import by.anabios13.courseworkmei.services.DirectoryService;
import by.anabios13.courseworkmei.services.NoteService;
import by.anabios13.courseworkmei.services.ProjectService;
import by.anabios13.courseworkmei.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class UserProfileController {
    private final UserService userService;
    private final ProjectService projectService;

    private final DirectoryService directoryService;
    private final NoteService noteService;
    private  int userID=1;//временное решение пока нет авторизации

    public UserProfileController(UserService userService, ProjectService projectService, DirectoryService directoryService, NoteService noteService) {
        this.userService = userService;
        this.projectService = projectService;
        this.directoryService = directoryService;
        this.noteService = noteService;
    }

    @GetMapping("/show/{id}")
    public ProfileDTO show(@PathVariable("id") int id){
        userID=id;
        ProfileDTO profileDTO= new ProfileDTO(userService.findOne(id));
        return profileDTO;
    }

    @PostMapping("/note/create")//создание заметки
    public ResponseEntity<Map<String,Integer>> createNote (@RequestBody NoteDTO noteDTO){
           return noteService.createNote(noteDTO,userID);
    }

    @PatchMapping("/note/edit")//patch запрос изменение заметки
    public ResponseEntity<Message> updateNote (@RequestBody NoteDTO noteDTO){
        return noteService.updateNote(noteDTO);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") int id) {
        noteService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/directory/create")//создание директории
    public DirectoryDTO createDirectory (@RequestBody DirectoryDTO directoryDTO){
        Directory directory = new Directory();
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.map(directoryDTO,directory);
        directoryService.save(directory,userID);
        modelMapper.map( directoryService.findOne(directory.getDirectoryId()),directoryDTO);
        return directoryDTO;
    }

    @PatchMapping("/directory/edit")//patch запрос изменение директории
    public String updateDirectory (@RequestBody DirectoryDTO directoryDTO){
        Directory directory = directoryService.findOne(directoryDTO.getDirectoryId());
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.map(directoryDTO,directory);
        directoryService.update(directoryDTO.getDirectoryId(), directory);
        return "Success";
    }

    @DeleteMapping("/directory/{id}")
    public String deleteDirectory(@PathVariable("id") int id) {
        directoryService.delete(id);
        return  "Success";
    }

    @PostMapping("/project/create")//создание проекта
    public ProjectDTO createProject (@RequestBody ProjectDTO projectDTO){
        Project project = new Project();
        project.setProjectOwner(userService.findOne(projectDTO.getProjectOwnerId()));
        project.setProjectDirectory(directoryService.findOne(projectDTO.getProjectDirectoryId()));
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.map(projectDTO,project);
        if(project.getProjectOwner()!=null && project.getProjectDirectory()!=null) {
            projectService.save(project, userID, projectDTO.getProjectDirectoryId());
            modelMapper.map(projectService.findOne(project.getProjectId()), projectDTO);
            return projectDTO;
        }
        return null;//временная заглушка
    }

    @PatchMapping("/project/edit")//patch запрос изменение проекта
    public String updateProject (@RequestBody ProjectDTO projectDTO){
        Project project = projectService.findOne(projectDTO.getProjectId());
        projectDTO.setTimeOfProjectCreation(project.getTimeOfProjectCreation());
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.map(projectDTO,project);
        projectService.update(projectDTO.getProjectId(), project);
        return "Success";
    }

    @DeleteMapping("/project/{id}")
    public String projectNote(@PathVariable("id") int id) {
        projectService.delete(id);
        return  "Success";
    }
}
