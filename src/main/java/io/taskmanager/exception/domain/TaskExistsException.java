package io.taskmanager.exception.domain;

public class TaskExistsException extends Exception{

    public TaskExistsException(String message) {
        super(message);
    }
}
