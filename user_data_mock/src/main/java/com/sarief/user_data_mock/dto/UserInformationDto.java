package com.sarief.user_data_mock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInformationDto {

    private String externalId;
    private String ranking;
}
