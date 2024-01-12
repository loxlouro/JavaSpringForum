package com.example.register2.repo;

import com.example.register2.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
}
