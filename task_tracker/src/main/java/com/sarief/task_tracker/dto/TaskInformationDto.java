package com.sarief.task_tracker.dto;

import com.sarief.task_tracker.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskInformationDto {

    private Long id;
    private String authorUsername;
    private String assigneeUsername;
    private String description;
    private List<CommentDto> comments;
    private List<String> fileLinks;
}
