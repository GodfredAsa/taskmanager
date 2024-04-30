package io.taskmanager.client.request;

import lombok.Getter;

import java.util.Set;

@Getter
public class AssignTaskRequest {
    private Set<String> assignees;
    private String taskId;
}
