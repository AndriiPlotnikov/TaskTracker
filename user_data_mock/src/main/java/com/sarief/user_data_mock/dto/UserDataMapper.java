package com.sarief.user_data_mock.dto;

import com.sarief.user_data_mock.entity.UserInformation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDataMapper {
    public UserInformationDto toDto(UserInformation userInformation) {
        return UserInformationDto.builder().externalId(userInformation.getId()).ranking(userInformation.getRanking()).build();
    }

}
