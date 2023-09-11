package by.anabios13.courseworkmei.services;

import by.anabios13.courseworkmei.models.User;
import by.anabios13.courseworkmei.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
private  final IUserRepository IUserRepository;

    @Autowired
    public UserService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public List<User> searchUserByUserName(String name) {
        return IUserRepository.findByUserNameContaining(name);
    }

    public User findOne(int id) {
        Optional<User> foundUser = IUserRepository.findById(id);
        return foundUser.orElse(null);
    }

    public List<User> findAll() {
        return IUserRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        IUserRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setUserId(id);
        IUserRepository.save(updatedUser);
    }

    public Optional<User> findUserByUserName(String username)  {
        Optional<User> user = IUserRepository.findByUserName(username);
        return user;
    }

    @Transactional
    public void delete(int id) {
        IUserRepository.deleteById(id);
    }
}
