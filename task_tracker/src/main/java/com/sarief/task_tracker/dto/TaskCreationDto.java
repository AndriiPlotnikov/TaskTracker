package com.sarief.task_tracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskCreationDto {
    private String description;
    private Long authorId;
    private Long assigneeId;
    private List<String> fileLinks;
}
