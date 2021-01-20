package com.sarief.task_tracker.rest;

import com.sarief.task_tracker.dto.*;
import com.sarief.task_tracker.entity.Comment;
import com.sarief.task_tracker.entity.Task;
import com.sarief.task_tracker.entity.User;
import com.sarief.task_tracker.repository.CommentRepository;
import com.sarief.task_tracker.repository.TaskRepository;
import com.sarief.task_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskInformationController {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @ResponseBody
    @PostMapping("/task")
    @Transactional
    public TaskInformationDto createTask(@RequestBody TaskCreationDto dto) {
        User author = userRepository.getOne(dto.getAuthorId());
        Task task = new Task();
        task.setCreated(new Date());
        task.setAuthor(author);
        task.setDescription(dto.getDescription());
        task.setAttachmentLinks(dto.getFileLinks());
        if (dto.getAssigneeId() != null) {
            User assignee = userRepository.getOne(dto.getAssigneeId());
            task.setAssignee(assignee);
        }

        task = taskRepository.save(task);
        task.setComments(new ArrayList<>());
        return toDto(task);
    }

    @ResponseBody
    @GetMapping("/task")
    @Transactional
    public TaskInformationDto getTaskInformation(@RequestParam Long id) {
        return toDto(taskRepository.getOne(id));
    }

    @ResponseBody
    @GetMapping("/tasks")
    @Transactional
    public List<TaskInformationDto> getTasksInformation(@RequestParam(required = false) Long groupId, @RequestParam Sort.Direction sortOrder) {
        List<Task> created = taskRepository.getByAssignee_group_id(groupId, Sort.by(sortOrder, "created"));
        return created.stream().map(this::toDto).collect(Collectors.toList());
    }

    @ResponseBody
    @PutMapping("/task/assign")
    @Transactional
    public TaskInformationDto assign(@RequestBody TaskAssignDto dto) {
        Task task = taskRepository.getOne(dto.getTaskId());
        if (dto.getAssigneeId() != null) {
            User assignee = userRepository.getOne(dto.getAssigneeId());
            task.setAssignee(assignee);
        } else {
            task.setAssignee(null);
        }

        task = taskRepository.save(task);
        return toDto(task);
    }

    @ResponseBody
    @PostMapping("/task/comment") // This probably meant for it's own controller, but it feels like this would be better here
    @Transactional
    public ResponseEntity<Void> comment(@RequestBody CreateCommentDto dto) {
        Task task = taskRepository.getOne(dto.getTaskId());
        User author = userRepository.getOne(dto.getAuthorId());
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setTask(task);
        comment.setContent(dto.getContent());
        commentRepository.save(comment);

        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @DeleteMapping("/task/comment")
    @Transactional
    public ResponseEntity<Void> deleteComment(@RequestParam Long id) {
        commentRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }


    private TaskInformationDto toDto(Task task) {
        String authorUsername = task.getAuthor().getUsername(); // author always exist => is not null
        String assigneeUsername = task.getAssignee() == null ? "" : task.getAssignee().getUsername();
        return TaskInformationDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .authorUsername(authorUsername)
                .assigneeUsername(assigneeUsername)
                .comments(task.getComments().stream().map(this::toDto).collect(Collectors.toList()))
                .fileLinks(task.getAttachmentLinks())
                .build();
    }

    private CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .authorUsername(comment.getAuthor().getUsername())
                .content(comment.getContent())
                .id(comment.getId())
                .build();
    }

}
