package io.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.taskmanager.enumeration.Priority;
import io.taskmanager.enumeration.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class Task implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String taskId;
    private String title;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
}
