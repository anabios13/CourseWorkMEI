package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.models.Person;
import by.anabios13.courseworkmei.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
private  final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> searchPersonByPersonName(String name) {
        return peopleRepository.findByPersonNameContaining(name);
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setPersonId(id);
        peopleRepository.save(updatedPerson);
    }

    public Optional<Person> findUserByPersonName(String username)  {
        Optional<Person> person = peopleRepository.findByPersonName(username);
        return person;
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
