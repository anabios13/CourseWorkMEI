package by.anabios13.courseworkmei.repositories;

import by.anabios13.courseworkmei.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface PeopleRepository extends JpaRepository<Person,Integer> {
        List<Person> findByPersonNameContaining(String personName);
        Optional<Person> findByPersonName(String personName);
    }
