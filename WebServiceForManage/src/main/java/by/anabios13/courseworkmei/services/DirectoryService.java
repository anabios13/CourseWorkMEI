package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.User;
import by.anabios13.courseworkmei.repositories.IDirectoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DirectoryService {
    private final IDirectoryRepository IDirectoryRepository;
    private final UserService userService;

    private final int directoriesPerPage = 15;//amount of directories in the page

    public DirectoryService(IDirectoryRepository IDirectoryRepository, UserService userService) {
        this.IDirectoryRepository = IDirectoryRepository;
        this.userService = userService;
    }


    //
    public List<Directory> findAll(Integer page) {
        return IDirectoryRepository.findAll(PageRequest.of(page, directoriesPerPage, Sort.by("directoryName").descending())).getContent();
    }

    //    public List<Directory> searchByName(String partOfNameTheBlank){
//        return directoryService.findByNameOfCarBlankContaining(partOfNameTheBlank);
//    }
    public List<Directory> searchDirectoryByDirectoryOwner(User noteOwner) {
        return IDirectoryRepository.findByDirectoryOwner(noteOwner);
    }


    public Directory findOne(int id) {
        Optional<Directory> foundDirectory = IDirectoryRepository.findById(id);
        return foundDirectory.orElse(null);
    }

    //Method for saving blank by authorisation user
    @Transactional
    public void save(Directory directory, Integer id) {
        directory.setDirectoryOwner(userService.findOne(id));//
        IDirectoryRepository.save(directory);
    }

    @Transactional
    public void update(int id, Directory updatedDirectory) {
        updatedDirectory.setDirectoryId(id);
        IDirectoryRepository.save(updatedDirectory);
    }

    @Transactional
    public void delete(int id) {
        IDirectoryRepository.deleteById(id);
    }
}
