package com.sarief.user_data_mock.repository;

import com.sarief.user_data_mock.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, String> {
}
