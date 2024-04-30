package io.taskmanager.service.impl;

import io.taskmanager.client.request.TaskRequest;
import io.taskmanager.client.response.HttpResponse;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.exception.domain.TaskExistsException;
import io.taskmanager.model.Task;
import io.taskmanager.repository.TaskRepository;
import io.taskmanager.service.TaskService;
import io.taskmanager.util.RandomUUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static io.taskmanager.constant.TaskConstants.TASK_EXISTS;
import static io.taskmanager.constant.TaskConstants.TASK_NOT_FOUND;
import static io.taskmanager.enumeration.TaskStatus.PENDING;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task findTaskByTaskId(String taskId) throws TaskNotFoundException {
        Optional<Task> optionalTask = taskRepository.findByTaskId(taskId);
        return optionalTask.orElseThrow(() -> new TaskNotFoundException(String.format(TASK_NOT_FOUND, taskId)));
    }

    @Override
    public Task createTask(TaskRequest request){
        return taskRepository.save(buildTask(request));
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }


    @Override
    public Task updateTask(TaskRequest request, String taskId) throws TaskNotFoundException {
        if(taskRepository.findByTaskId(taskId).isPresent()) {
            Task task = buildTask(request);
            task.setStatus(request.getStatus());
            return taskRepository.save(task);
        }
       throw new TaskNotFoundException(TASK_NOT_FOUND);
    }

    @Override
    public void deleteTask(String taskId) throws TaskNotFoundException {
        Optional<Task> task = taskRepository.findByTaskId(taskId);
        if(task.isPresent()) {
            taskRepository.deleteById(task.get().getId());
        }
        else{
            throw new TaskNotFoundException(TASK_NOT_FOUND);
        }
    }


    private Task buildTask(TaskRequest request){
        LocalDate dueDate = LocalDate.now();
        return Task.builder()
                .taskId(new RandomUUIDGenerator().generateRandomUUID())
                .dueDate(dueDate.plusDays(request.getTaskDuration()))
                .title(request.getTitle())
                .status(PENDING)
                .priority(request.getPriority())
                .build();
    }
}
