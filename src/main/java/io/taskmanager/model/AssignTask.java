package io.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "assign_tasks")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignTask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskAssignmentId;
    private Set<String> assignees;
    private String taskId;
}
