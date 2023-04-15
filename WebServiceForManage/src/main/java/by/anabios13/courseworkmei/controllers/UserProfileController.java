package by.anabios13.courseworkmei.controllers;

import by.anabios13.courseworkmei.dto.ProfileDTO;
import by.anabios13.courseworkmei.services.DirectoryService;
import by.anabios13.courseworkmei.services.NoteService;
import by.anabios13.courseworkmei.services.PeopleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileController {
    private final PeopleService peopleService;

    private final DirectoryService directoryService;
    private final NoteService noteService;

    public UserProfileController(PeopleService peopleService, DirectoryService directoryService, NoteService noteService) {
        this.peopleService = peopleService;
        this.directoryService = directoryService;
        this.noteService = noteService;
    }


    @GetMapping("/show/{id}")
    public ProfileDTO show(@PathVariable("id") int id){

      ProfileDTO profileDTO= new ProfileDTO(peopleService.findOne(id));
        return profileDTO;
    }
}
