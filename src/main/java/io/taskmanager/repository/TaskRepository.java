package io.taskmanager.repository;

import io.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskId(String taskId);

    @Query("select (count(t) > 0) from Task t where t.title = ?1")
    boolean existsByTitle(String title);
}
