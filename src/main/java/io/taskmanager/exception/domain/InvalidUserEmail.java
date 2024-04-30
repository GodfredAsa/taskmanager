package io.taskmanager.exception.domain;

public class InvalidUserEmail extends Exception {
    public InvalidUserEmail(String message) {
        super(message);
    }
}
