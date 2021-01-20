package com.sarief.task_tracker.repository;

import com.sarief.task_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getOneByUsername(String username);
}
