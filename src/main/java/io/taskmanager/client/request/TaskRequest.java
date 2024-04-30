package io.taskmanager.client.request;

import io.taskmanager.enumeration.Priority;
import io.taskmanager.enumeration.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class TaskRequest {
    private String title;
    private long taskDuration;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
}
