package com.example.register2.repo;

import com.example.register2.models.ProfileImage;
import com.example.register2.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    ProfileImage findByUserAccount(UserAccount userAccount);
}
