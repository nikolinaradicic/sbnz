package com.sbnz.sbnzproject.repository;

import com.sbnz.sbnzproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
