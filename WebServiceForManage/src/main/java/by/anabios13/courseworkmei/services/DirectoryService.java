package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.Note;
import by.anabios13.courseworkmei.models.Person;
import by.anabios13.courseworkmei.repositories.DirectoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DirectoryService {
    private final DirectoryRepository directoryRepository;
    private final PeopleService peopleService;

    private final int directoriesPerPage = 15;//amount of directories in the page

    public DirectoryService(DirectoryRepository directoryRepository, PeopleService peopleService) {
        this.directoryRepository = directoryRepository;
        this.peopleService = peopleService;
    }


    //
    public List<Directory> findAll(Integer page) {
        return directoryRepository.findAll(PageRequest.of(page, directoriesPerPage, Sort.by("directoryName").descending())).getContent();
    }

    //    public List<Directory> searchByName(String partOfNameTheBlank){
//        return directoryService.findByNameOfCarBlankContaining(partOfNameTheBlank);
//    }
    public List<Directory> searchDirectoryByDirectoryOwner(Person noteOwner) {
        return directoryRepository.findByDirectoryOwner(noteOwner);
    }


    public Directory findOne(int id) {
        Optional<Directory> foundDirectory = directoryRepository.findById(id);
        return foundDirectory.orElse(null);
    }

    //Method for saving blank by authorisation user
    @Transactional
    public void save(Directory directory, Integer id) {
        directory.setDirectoryOwner(peopleService.findOne(id));//
        directoryRepository.save(directory);
    }

    @Transactional
    public void update(int id, Directory updatedDirectory) {
        updatedDirectory.setDirectoryId(id);
        directoryRepository.save(updatedDirectory);
    }

    @Transactional
    public void delete(int id) {
        directoryRepository.deleteById(id);
    }
}
