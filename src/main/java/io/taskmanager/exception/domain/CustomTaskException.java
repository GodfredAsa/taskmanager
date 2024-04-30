package io.taskmanager.exception.domain;

public class CustomTaskException extends Exception {
    public CustomTaskException(String message) {
        super(message);
    }

    public static class TaskNotFoundException extends Exception {
        public TaskNotFoundException(String message) {
            super(message);
        }
    }
}
