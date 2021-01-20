package com.sarief.user_data_mock.rest;

import com.sarief.user_data_mock.dto.UserDataMapper;
import com.sarief.user_data_mock.dto.UserInformationDto;
import com.sarief.user_data_mock.entity.UserInformation;
import com.sarief.user_data_mock.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
public class UserDataEndpoint {

    public static final int DEFAULT_SIZE_OF_ID = 10;
    private final UserInformationRepository userInformationRepository;
    private final UserDataMapper userDataMapper;

    @ResponseBody
    @GetMapping("/user")
    @Transactional
    public UserInformationDto getUserData(@RequestParam("userId") String userId) {
        // Let's just mock some random values
        return userDataMapper.toDto(userInformationRepository.getOne(userId));
    }

    @ResponseBody
    @PostMapping("/user")
    @Transactional
    public UserInformationDto createRecord() {
        String userGeneratedId = RandomStringUtils.randomAlphanumeric(DEFAULT_SIZE_OF_ID);
        //for simplicity assume it was changed by external program somewhere
        int randomRanking = RandomUtils.nextInt(0, 10);

        UserInformation userInformation = UserInformation.builder()
                .id(userGeneratedId)
                .ranking(Integer.toString(randomRanking))
                .build();
        userInformation = userInformationRepository.save(userInformation);

        return userDataMapper.toDto(userInformation);
    }


}
