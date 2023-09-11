package by.anabios13.courseworkmei.repositories;

import by.anabios13.courseworkmei.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface IUserRepository extends JpaRepository<User,Integer> {
        List<User> findByUserNameContaining(String userName);

        Optional<User> findByUserName(String userName);


    }
