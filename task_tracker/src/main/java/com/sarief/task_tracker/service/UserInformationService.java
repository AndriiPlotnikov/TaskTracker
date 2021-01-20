package com.sarief.task_tracker.service;

/**
 * Service that works with user's additional information
 */
public interface UserInformationService {
    String fetchRanking(String externalId);

    String registerUser();
}
