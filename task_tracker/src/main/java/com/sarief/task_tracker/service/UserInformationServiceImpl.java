package com.sarief.task_tracker.service;

import com.sarief.task_tracker.dto.UserRankingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * default implementation for {@link UserInformationService}
 */
@Service
@Slf4j
public class UserInformationServiceImpl implements UserInformationService {
    private static final String BASE_URL = "http://localhost:8081/user"; // properties are better, but...
    private static final String DEFAULT_ANSWER = "0";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String fetchRanking(String externalId) {
        ResponseEntity<UserRankingDto> response;
        try {
            response = restTemplate.getForEntity(BASE_URL + "?userId={externalId}", UserRankingDto.class, externalId);
        } catch (Exception e) {
            log.error("Error occurred when fetching ranking", e);
            return DEFAULT_ANSWER;
        }
        return response.getBody().getRanking(); // assume if no exception that body exists
    }

    @Override
    public String registerUser() {
        ResponseEntity<UserRankingDto> stringResponseEntity = restTemplate.postForEntity(BASE_URL, "", UserRankingDto.class);
        return stringResponseEntity.getBody().getExternalId();
    }
}
