package io.taskmanager.service;

import io.taskmanager.client.request.RegistrationRequest;
import io.taskmanager.exception.domain.*;
import io.taskmanager.model.User;

import java.util.List;

public interface UserService {

    User findUserByUserId(String userId) throws UserNotFoundException;
    User registerUser(RegistrationRequest request) throws EmailExistException, InvalidUserEmail;

    List<User> findAllUsers();

    boolean existsByUserId(String userId);
    boolean userExistsByEmail(String email);
}
