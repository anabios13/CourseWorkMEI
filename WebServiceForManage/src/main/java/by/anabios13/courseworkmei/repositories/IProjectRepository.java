package by.anabios13.courseworkmei.repositories;


import by.anabios13.courseworkmei.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project,Integer> {

}
