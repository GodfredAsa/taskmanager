package io.taskmanager.service;

import io.taskmanager.client.request.TaskRequest;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.model.Task;

import java.util.List;

public interface TaskService {
    Task findTaskByTaskId(String taskId) throws TaskNotFoundException;
    Task createTask(TaskRequest request);
    List<Task> findAllTasks();
    Task updateTask(TaskRequest request, String taskId) throws TaskNotFoundException;
    void deleteTask(String taskId) throws TaskNotFoundException;
}
