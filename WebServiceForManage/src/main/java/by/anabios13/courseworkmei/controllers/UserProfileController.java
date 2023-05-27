package by.anabios13.courseworkmei.controllers;

import by.anabios13.courseworkmei.dto.DirectoryDTO;
import by.anabios13.courseworkmei.dto.NoteDTO;
import by.anabios13.courseworkmei.dto.ProfileDTO;
import by.anabios13.courseworkmei.dto.ProjectDTO;
import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.Note;
import by.anabios13.courseworkmei.models.Project;
import by.anabios13.courseworkmei.services.DirectoryService;
import by.anabios13.courseworkmei.services.NoteService;
import by.anabios13.courseworkmei.services.PeopleService;
import by.anabios13.courseworkmei.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class UserProfileController {
    private final PeopleService peopleService;
    private final ProjectService projectService;

    private final DirectoryService directoryService;
    private final NoteService noteService;
    private  int personID=0;//временное решение пока нет авторизации

    public UserProfileController(PeopleService peopleService, ProjectService projectService, DirectoryService directoryService, NoteService noteService) {
        this.peopleService = peopleService;
        this.projectService = projectService;
        this.directoryService = directoryService;
        this.noteService = noteService;
    }

//   @RequestMapping(value = "/show/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ProfileDTO> show(@PathVariable("id") int id){
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Access-Control-Allow-Origin", "*");
//      ProfileDTO profileDTO= new ProfileDTO(peopleService.findOne(id));
//
//      //  return ResponseEntity.ok().headers(headers).body(profileDTO);
//    }
    @GetMapping("/show/{id}")
    public ProfileDTO show(@PathVariable("id") int id){
        personID=id;
        ProfileDTO profileDTO= new ProfileDTO(peopleService.findOne(id));
        return profileDTO;
    }

    @PostMapping("/note/create")//создание заметки
    public NoteDTO createNote (@RequestBody NoteDTO noteDTO){
     Note note = new Note();
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.map(noteDTO,note);
        noteService.save(note,personID);
        modelMapper.map( noteService.findOne(note.getNoteId()),noteDTO);
        noteDTO.setNoteText(null);
        return noteDTO;
    }

    @PatchMapping("/note/edit")//patch запрос изменение заметки
    public String updateNote (@RequestBody NoteDTO noteDTO){
        Note note = noteService.findOne(noteDTO.getNoteId());
        note.setNoteText(noteDTO.getNoteText());
        noteService.update(noteDTO.getNoteId(), note);
        return "Success";
    }

    @PostMapping("/directory/create")//создание директории
    public DirectoryDTO createDirectory (@RequestBody DirectoryDTO directoryDTO){
        Directory directory = new Directory();
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.map(directoryDTO,directory);
        directoryService.save(directory,personID);
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

    @PostMapping("/project/create")//создание проекта
    public ProjectDTO createProject (@RequestBody ProjectDTO projectDTO){
        Project project = new Project();
        project.setProjectOwner(peopleService.findOne(projectDTO.getProjectOwnerId()));
        project.setProjectDirectory(directoryService.findOne(projectDTO.getProjectDirectoryId()));
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.map(projectDTO,project);
        if(project.getProjectOwner()!=null && project.getProjectDirectory()!=null) {
            projectService.save(project, personID, projectDTO.getProjectDirectoryId());
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

}
