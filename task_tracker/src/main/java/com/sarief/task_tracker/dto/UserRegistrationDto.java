package com.sarief.task_tracker.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private Long groupId;
}
