package com.boot.graphql.service;

import com.boot.graphql.dtos.UserDTO;
import com.boot.graphql.entity.User;
import com.boot.graphql.repo.UserRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int userId, String userName, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (userName != null && !userName.isEmpty()) {
            user.setUserName(userName);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }

        return userRepository.save(user);
    }


    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id : " + userId));

    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            log.warn("No users found");
            throw new RuntimeException("No users found");
        }
        log.info("fetch all users : {}", all);
        return all;
    }

    @Override
    public boolean existByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return false;
        }
        return userRepository.existsByUserName(userName);

    }

    @Override
    public List<User> findByUserNameContaining(String userName) {
        if (userName == null || userName.isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.findByUserNameContaining(userName);
    }

    @Override
    public String deleteUserById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " deleted successfully.";
        }

        return "User with ID " + id + " not found.";
    }

    @Override
    public List<User> getAllUserInDescendingOrder() {
        return userRepository.findAllByOrderByUserIdDesc();
    }


}
