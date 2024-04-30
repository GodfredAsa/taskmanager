package io.taskmanager.service.impl;

import io.taskmanager.client.request.CommentRequest;
import io.taskmanager.client.response.CommentResponse;
import io.taskmanager.exception.domain.CommentNotFoundException;
import io.taskmanager.exception.domain.CustomCommentException;
import io.taskmanager.exception.domain.TaskNotFoundException;
import io.taskmanager.exception.domain.UserNotFoundException;
import io.taskmanager.model.Comment;
import io.taskmanager.model.Task;
import io.taskmanager.model.User;
import io.taskmanager.repository.CommentRepository;
import io.taskmanager.service.CommentService;
import io.taskmanager.service.TaskService;
import io.taskmanager.service.UserService;
import io.taskmanager.util.RandomUUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import static io.taskmanager.constant.CommentConstant.COMMENT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskService;
    private final UserService userService;

    @Override
    public CommentResponse commentOnTask(CommentRequest request) throws TaskNotFoundException, UserNotFoundException, CustomCommentException {
        Comment comment = commentRepository.save(buildComment(request));
        return CommentResponse.builder()
                .user(userService.findUserByUserId(comment.getUserId()))
                .task(taskService.findTaskByTaskId(comment.getTaskId()))
                .commentId(comment.getCommentId())
                .description(comment.getDescription())
                .build();

    }

    private Comment buildComment(CommentRequest request) throws TaskNotFoundException, UserNotFoundException, CustomCommentException {
        System.out.println("==================== User User clean up  =======================");
        User user = userService.findUserByUserId(request.getUserId());
        Task task = taskService.findTaskByTaskId(request.getTaskId());
        if(user != null && task != null){
            return Comment.builder()
                    .description(request.getDescription())
                    .commentId(new RandomUUIDGenerator().generateRandomUUID())
                    .userId(request.getUserId())
                    .taskId(request.getTaskId())
                    .build();
        }else{
            throw new CustomCommentException(COMMENT_NOT_FOUND);

        }
    }

    @Override
    public List<Comment> findAllComments(){
        return commentRepository.findAll();
    }

    @Override
    public Comment findCommentByCommentId(String commentId) throws CustomCommentException {
        if(commentRepository.existsByCommentId(commentId)){
            return commentRepository.findByCommentId(commentId);
        }
        throw new CustomCommentException(COMMENT_NOT_FOUND);
    }

    public void  deleteCommentById(String commentId) throws CommentNotFoundException {
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        if (comment != null) {
            commentRepository.deleteById(comment.getId());
        }else{
            throw new CommentNotFoundException(COMMENT_NOT_FOUND);
        }
    }

    @Override
    public Comment updateComment( String description, String commentId) throws CommentNotFoundException {
        if(commentRepository.existsByCommentId(commentId)){
            Comment comment = commentRepository.findCommentByCommentId(commentId);
            comment.setDescription(description);
            return commentRepository.save(comment);
        }else{
            throw new CommentNotFoundException(COMMENT_NOT_FOUND);
        }
    }
}
