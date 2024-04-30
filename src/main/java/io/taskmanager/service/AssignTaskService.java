package io.taskmanager.service;

import io.taskmanager.client.request.AssignTaskRequest;
import io.taskmanager.model.AssignTask;

import java.util.List;

public interface AssignTaskService {
    AssignTask assignTaskToUser(AssignTaskRequest assignTask);

    List<AssignTask> getAllAssignTasks();
}
