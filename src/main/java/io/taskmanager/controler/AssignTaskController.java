package io.taskmanager.controler;
import io.taskmanager.client.request.AssignTaskRequest;
import io.taskmanager.model.AssignTask;
import io.taskmanager.service.AssignTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assign")
@RequiredArgsConstructor
public class AssignTaskController {

    private final AssignTaskService assignTaskService;

    @PostMapping
    public AssignTask assignTaskToUser(@RequestBody AssignTaskRequest request){
        return assignTaskService.assignTaskToUser(request);
    }

    @GetMapping
    public List<AssignTask> getAllAssignedTasks(){
        return assignTaskService.getAllAssignTasks();
    }

}
