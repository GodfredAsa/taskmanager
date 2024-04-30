package io.taskmanager.client.request;

import lombok.Getter;

@Getter
public class CommentRequest {
    private String description;
    private String taskId;
    private String userId;
}
