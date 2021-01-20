package com.sarief.task_tracker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupInformationDto {

    private Long id;
    private String name;
    private List<UserInformationDto> users;
}
