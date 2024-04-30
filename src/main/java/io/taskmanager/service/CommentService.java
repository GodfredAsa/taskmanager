package io.taskmanager.service;

import io.taskmanager.client.request.CommentRequest;
import io.taskmanager.client.response.CommentResponse;
import io.taskmanager.exception.domain.CommentNotFoundException;
import io.taskmanager.exception.domain.CustomCommentException;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.exception.domain.UserNotFoundException;
import io.taskmanager.model.Comment;

import java.util.List;

public interface CommentService {

     CommentResponse commentOnTask(CommentRequest comment) throws TaskNotFoundException, UserNotFoundException, CustomCommentException;
     Comment findCommentByCommentId(String commentId) throws CustomCommentException;
     List<Comment> findAllComments();
     void deleteCommentById(String commentId) throws CommentNotFoundException;
     Comment updateComment(String description, String commentId) throws CommentNotFoundException;
}
