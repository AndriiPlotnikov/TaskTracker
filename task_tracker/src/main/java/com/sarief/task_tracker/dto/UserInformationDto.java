package com.sarief.task_tracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInformationDto {

    private Long id;
    private String username;
    private String ranking;
    private Long groupId;
}
