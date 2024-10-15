package com.boot.graphql.controller;

import com.boot.graphql.dtos.UserDTO;
import com.boot.graphql.entity.User;
import com.boot.graphql.service.IUserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Log4j2
public class UserController {
    @Autowired
    private IUserService userService;

    @MutationMapping
    public User createUser(@Argument String userName, @Argument String email, @Argument String phone, @Argument String password) {
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        log.info("Create user : {}", user);
        return userService.createUser(user);

    }

    @MutationMapping
    public User updateUser(@Argument int userId, @Argument String userName, @Argument String email, @Argument String phone, @Argument String password) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        log.info("Update user : {}", user);
        return userService.updateUser(user);
    }

    @QueryMapping
    public User getUserById(@Argument int userId) {
        User userById = userService.getUserById(userId);
        log.info("Fetch user by id : {}", userId);
        log.info("User derails : {}", userById);
        return userById;

    }

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @MutationMapping
    public String deleteUserById(@Argument int userId) {
        log.info("Deleting user with ID: {}", userId);
        return userService.deleteUserById(userId);
    }

    @MutationMapping
    public User createUserByDTO(@Argument UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        return userService.createUser(user);
    }


    @QueryMapping
    public List<User> getAllUserInDescendingOrder() {
        log.info("Fetching all users in descending order");
        return userService.getAllUserInDescendingOrder();
    }

    @MutationMapping
    public boolean existByUserName(@Argument String userName) {
        return userService.existByUserName(userName);
    }

    @QueryMapping
    public List<User> findByUserNameContaining(@Argument String userName) {
        log.info("Fetching users by userName containing: {}", userName);
        return userService.findByUserNameContaining(userName);
    }

}
