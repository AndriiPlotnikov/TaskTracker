package com.sarief.task_tracker.repository;

import com.sarief.task_tracker.entity.Comment;
import com.sarief.task_tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
