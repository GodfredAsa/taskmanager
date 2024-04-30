package io.taskmanager.client.request;

import io.taskmanager.enumeration.Role;
import lombok.Getter;

@Getter
public class RegistrationRequest {
    private String username;
    private String gender;
    private String email;
    private String password;
    private Role role;
}
