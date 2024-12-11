package test.xpertgroup.test_backend.services;


import org.springframework.stereotype.Service;
import test.xpertgroup.test_backend.entities.User;
import test.xpertgroup.test_backend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}