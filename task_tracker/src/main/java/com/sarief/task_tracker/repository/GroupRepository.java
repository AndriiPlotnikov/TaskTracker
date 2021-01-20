package com.sarief.task_tracker.repository;

import com.sarief.task_tracker.entity.Group;
import com.sarief.task_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Group repository
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
