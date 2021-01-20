package com.sarief.task_tracker.rest;

import com.sarief.task_tracker.repository.TaskRepository;
import com.sarief.task_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Report related operation controller
 */
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final UserRepository userRepository; // this probably better be reportService but good enough for it's purpose
    private final TaskRepository taskRepository;

    @ResponseBody
    @GetMapping("/users")
    public String reportRegisteredUsers() {
        return "Registered Users: " + userRepository.count();
    }

    @ResponseBody
    @GetMapping("/tasks")
    public String reportTasksCreated() {
        return "Created Tasks: " + taskRepository.count();
    }

}
