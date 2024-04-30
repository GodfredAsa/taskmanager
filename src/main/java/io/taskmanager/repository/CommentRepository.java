package io.taskmanager.repository;

import io.taskmanager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByCommentId(String commentId);

    @Query("select (count(c) > 0) from Comment c where c.commentId = ?1")
    boolean existsByCommentId(String commentId);

    Comment findCommentByCommentId(String commentId);



}
