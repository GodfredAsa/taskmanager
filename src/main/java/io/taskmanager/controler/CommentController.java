package io.taskmanager.controler;

import io.taskmanager.client.request.CommentRequest;
import io.taskmanager.client.response.CommentResponse;
import io.taskmanager.exception.domain.CommentNotFoundException;
import io.taskmanager.exception.domain.CustomCommentException;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.exception.domain.UserNotFoundException;
import io.taskmanager.model.Comment;
import io.taskmanager.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController( CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponse createComment(@RequestBody CommentRequest comment) throws TaskNotFoundException, UserNotFoundException, CustomCommentException {
        return commentService.commentOnTask(comment);
    }

    @GetMapping
    public ResponseEntity<Comment> findCommentByCommentId(@RequestParam("commentId") String commentId) throws CustomCommentException, UserNotFoundException, TaskNotFoundException {
        return new ResponseEntity<>(commentService.findCommentByCommentId(commentId), OK);
    }

    @GetMapping("/all")
    public List<Comment> getCommentByCommentId(){
        return commentService.findAllComments();
    }

    @DeleteMapping
    public void deleteCommentById(@RequestParam String commentId) throws CommentNotFoundException {
         commentService.deleteCommentById(commentId);
    }

    @PutMapping
    public void updateComment(@RequestParam String description, @RequestParam String commentId) throws CommentNotFoundException {
        commentService.updateComment(description, commentId);
    }
}
