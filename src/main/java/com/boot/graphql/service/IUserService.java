package com.boot.graphql.service;

import com.boot.graphql.dtos.UserDTO;
import com.boot.graphql.entity.User;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;

public interface IUserService {
    User createUser(User user);

    User updateUser(int userId, String userName, String email);

    User getUserById(int userId);

    List<User> getAllUsers();

    boolean existByUserName(String userName);

    List<User> findByUserNameContaining(String userName);

    String deleteUserById(int id);

    List<User> getAllUserInDescendingOrder();

    String allUserPDFGenerator();



}
