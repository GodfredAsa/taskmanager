package io.taskmanager.service.impl;

import io.taskmanager.client.request.RegistrationRequest;
import io.taskmanager.enumeration.Role;
import io.taskmanager.exception.domain.UserExistsException;
import io.taskmanager.exception.domain.*;
import io.taskmanager.model.User;
import io.taskmanager.repository.UserRepository;
import io.taskmanager.service.UserService;
import io.taskmanager.util.RandomUUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static io.taskmanager.constant.Authority.USER_AUTHORITIES;
import static io.taskmanager.constant.UserConstant.*;
import static io.taskmanager.enumeration.Role.USER;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Override
    public User findUserByUserId(String userId) throws UserNotFoundException {
        try{
            return userRepository.findByUserId(userId);
        }catch (Exception e){
            throw new UserNotFoundException("User not found");
        }

    }

    @Override
    public User registerUser(RegistrationRequest request) throws EmailExistException, InvalidUserEmail {
        validateUserRegistrationDetails(request);
        isExistingUser(request.getEmail());
        User user = buildUser(request);
        userRepository.save(user);
        log.info(String.valueOf(user));
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository
                .findAll().stream()
                .filter(user -> !user.getRole().equals(Role.SUPERADMIN))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.userExistsByEmail(email);
    }

    private void validateUserRegistrationDetails(RegistrationRequest request) throws IllegalArgumentException, InvalidUserEmail {
             if(isValidEmail(request.getEmail())){
                 validateUserPreferredNameAndPassword(request.getUsername(), request.getPassword());
             }else{
                 throw  new InvalidUserEmail(INVALID_EMAIL);
             }
    }

    private void validateUserPreferredNameAndPassword(String username, String password) throws  IllegalArgumentException{
        if(username.trim().length() <= 3) throw new IllegalArgumentException(String.format(FIELD_LESS_THAN_FOUR_CHARACTERS, "username", username));
        if (password.trim().length() <= 3) throw new IllegalArgumentException(String.format(FIELD_LESS_THAN_FOUR_CHARACTERS, "password", password));
    }

    private void isExistingUser(String email) throws EmailExistException {
        if(userRepository.existsByEmail(email)){
            throw new EmailExistException(EMAIL_ALREADY_EXISTS);
        }
    }

    private User buildUser(RegistrationRequest request){
        return User.builder()
                .userId(new RandomUUIDGenerator().generateRandomUUID())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodePassword(request.getPassword()))
                .gender(request.getGender())
                .joinDate(new Date())
                .role(USER)
                .authorities(USER_AUTHORITIES)
                .build();
    }

    public void findUserByUsername(String username) throws UserExistsException {
        User user = userRepository.findByUsername(username);
        if(user != null) throw new UserExistsException("User Already Exists");
    }

    private static boolean isValidEmail(String email){
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static String encodePassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
