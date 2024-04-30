package io.taskmanager.client.response;

import io.taskmanager.model.Task;
import io.taskmanager.model.User;
import lombok.Builder;

@Builder
public class CommentResponse {

    private String commentId;
    private String description;
    private Task task;
    private User user;
}
