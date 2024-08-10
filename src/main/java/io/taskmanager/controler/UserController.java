package io.taskmanager.controler;

import io.taskmanager.client.request.RegistrationRequest;
import io.taskmanager.client.response.HttpResponse;
import io.taskmanager.exception.ExceptionHandling;
import io.taskmanager.exception.domain.*;
import io.taskmanager.model.User;
import io.taskmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Slf4j
@RequestMapping(path = {"/", "/api/v1/users"})
public class UserController extends ExceptionHandling {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody RegistrationRequest request) throws EmailExistException, GenderException, InvalidUserEmail {
        if(userService.userExistsByEmail(request.getEmail())){
            HttpResponse httpResponse = new HttpResponse(400, BAD_REQUEST,
                    "Email Already Exists",
                    "Email already taken");
            log.error(String.format("%s is already taken", request.getEmail()));
            return new ResponseEntity<>(httpResponse, BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.registerUser(request), CREATED);
    }

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

}
