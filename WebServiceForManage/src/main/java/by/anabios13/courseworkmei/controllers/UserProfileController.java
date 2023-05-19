package by.anabios13.courseworkmei.controllers;

import by.anabios13.courseworkmei.dto.ProfileDTO;
import by.anabios13.courseworkmei.services.DirectoryService;
import by.anabios13.courseworkmei.services.NoteService;
import by.anabios13.courseworkmei.services.PeopleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class UserProfileController {
    private final PeopleService peopleService;

    private final DirectoryService directoryService;
    private final NoteService noteService;


    public UserProfileController(PeopleService peopleService, DirectoryService directoryService, NoteService noteService) {
        this.peopleService = peopleService;
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
        ProfileDTO profileDTO= new ProfileDTO(peopleService.findOne(id));
        return profileDTO;
    }

}
