package com.sarief.task_tracker.dto;

import lombok.Data;

@Data
public class CreateCommentDto {
    private Long taskId;
    private Long authorId;
    private String content;
}
