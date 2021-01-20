package com.sarief.task_tracker.dto;

import lombok.Data;

@Data
public class TaskAssignDto {
    private Long taskId;
    private Long assigneeId;
}
