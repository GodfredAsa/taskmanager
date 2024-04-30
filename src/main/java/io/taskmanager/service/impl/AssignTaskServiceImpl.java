package io.taskmanager.service.impl;

import io.taskmanager.client.request.AssignTaskRequest;
import io.taskmanager.model.AssignTask;
import io.taskmanager.repository.AssignTaskRepository;
import io.taskmanager.service.AssignTaskService;
import io.taskmanager.util.RandomUUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AssignTaskServiceImpl implements AssignTaskService {
    private final AssignTaskRepository assignTaskRepository;

    @Override
    public AssignTask assignTaskToUser(AssignTaskRequest request){
        AssignTask assignTask = new AssignTask();
        for (String assignee: request.getAssignees()) {
             assignTask = AssignTask.builder()
                    .taskAssignmentId(new RandomUUIDGenerator().generateRandomUUID())
                    .taskId(request.getTaskId())
                    .assignees(Set.of(assignee))
                    .build();
            assignTaskRepository.save(assignTask);
        }
        return assignTask;
    }

    @Override
    public List<AssignTask> getAllAssignTasks(){
       return assignTaskRepository.findAll();
    }

}
