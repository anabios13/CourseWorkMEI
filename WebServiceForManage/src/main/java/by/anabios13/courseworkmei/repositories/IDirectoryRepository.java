package by.anabios13.courseworkmei.repositories;

import by.anabios13.courseworkmei.models.Directory;
import by.anabios13.courseworkmei.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDirectoryRepository extends JpaRepository<Directory,Integer> {
    List<Directory> findByDirectoryOwner(User directoryOwner);
}
