package com.sarief.task_tracker.rest;

import com.sarief.task_tracker.dto.CreateGroupDto;
import com.sarief.task_tracker.dto.GroupInformationDto;
import com.sarief.task_tracker.dto.UserInformationDto;
import com.sarief.task_tracker.dto.UserRegistrationDto;
import com.sarief.task_tracker.entity.Group;
import com.sarief.task_tracker.entity.User;
import com.sarief.task_tracker.repository.GroupRepository;
import com.sarief.task_tracker.repository.UserRepository;
import com.sarief.task_tracker.service.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * User related operation controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInformationController {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserInformationService userInformationService;

    @ResponseBody
    @PostMapping
    public UserInformationDto createUser(@RequestBody UserRegistrationDto dto) {
        User user = User.builder().username(dto.getUsername()).password(Integer.toString(dto.getPassword().hashCode())).build();
        String externalId = userInformationService.registerUser();
        user.setExternalId(externalId);
        user.setRegistered(new Date());
        if (dto.getGroupId() != null) {
            Group group = groupRepository.getOne(dto.getGroupId());
            user.setGroup(group);
        }
        user = userRepository.save(user);

        // although we have ranking due to mock, in case it was real there would be no rank yet, so skip
        return toDto(user);
    }

    @ResponseBody
    @PostMapping("/group")
    public GroupInformationDto createGroup(@RequestBody CreateGroupDto dto) {
        Group group = new Group();
        group.setName(dto.getName());
        group = groupRepository.save(group);

        return GroupInformationDto.builder().id(group.getId()).name(group.getName()).build();
    }

    @ResponseBody
    @GetMapping("/group")
    public GroupInformationDto getGroupInformation(@RequestParam Long id) {
        Group group = groupRepository.getOne(id);
        return GroupInformationDto.builder()
                .id(group.getId())
                .name(group.getName())
                .users(group.getUsers().stream().map(this::toDto).collect(Collectors.toList()))
                .build();
    }

    @ResponseBody
    @GetMapping("/info")
    public UserInformationDto getUserInformation(@RequestParam Long id) {
        // This should be either limited to admin or get info from logged in information
        User user = userRepository.getOne(id);
        String ranking = userInformationService.fetchRanking(user.getExternalId());

        return toDto(user, ranking);
    }

    private UserInformationDto toDto(User user) {
        return toDto(user, null);
    }

    private UserInformationDto toDto(User user, String ranking) {
        return UserInformationDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .ranking(ranking)
                .groupId(user.getGroup() == null ? null : user.getGroup().getId())
                .build();
    }

}
