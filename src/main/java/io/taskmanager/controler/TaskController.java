package io.taskmanager.controler;

import io.taskmanager.client.request.TaskRequest;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.model.Task;
import io.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Task>  findTaskByTaskId(@RequestParam("taskId") String taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.findTaskByTaskId(taskId), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task>  createTask(@RequestBody TaskRequest request){
        return new ResponseEntity<>(taskService.createTask(request), OK);
    }

    @GetMapping("/all")
    public List<Task> findAllTasks() {
            return taskService.findAllTasks();
    }

    @PutMapping
    public Task updateTask(@RequestBody TaskRequest taskRequest, @RequestParam("taskId") String taskId) throws TaskNotFoundException {
        return taskService.updateTask(taskRequest, taskId);
    }

    @DeleteMapping("/{taskId}")
    public void DeleteTaskById(@PathVariable String taskId) throws TaskNotFoundException {
        taskService.deleteTask(taskId);
    }

}