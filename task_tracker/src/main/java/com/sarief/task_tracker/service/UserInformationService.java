package com.sarief.task_tracker.service;

/**
 * Service that works with user's additional information
 */
public interface UserInformationService {
    /**
     * fetch ranking from external service
     *
     * @param externalId id in external service
     * @return ranking found
     */
    String fetchRanking(String externalId);

    /**
     * register user in external service. Id automatically generated
     *
     * @return created user external id
     */
    String registerUser();
}
