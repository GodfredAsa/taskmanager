package io.taskmanager.model;

import io.taskmanager.client.response.CommentResponse;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.exception.domain.UserNotFoundException;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "comments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Comment{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentId;
    private String description;
    private String taskId;
    private String userId;
}
