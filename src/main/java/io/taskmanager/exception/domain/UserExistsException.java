package io.taskmanager.exception.domain;

public class UserExistsException extends Exception {

    public UserExistsException(String message) {
        super(message);
    }
}
