package com.sarief.task_tracker.repository;

import com.sarief.task_tracker.entity.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Task repository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> getByAssignee_group_id(Long groupId, Sort sort);
}
