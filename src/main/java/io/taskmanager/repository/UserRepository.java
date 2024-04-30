package io.taskmanager.repository;

import io.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);

    @Query("select (count(u) > 0) from User u where u.userId = ?1")
    boolean existsByUserId(String userId);

    User findByUserId(String userId);

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean userExistsByEmail(String email);











}
