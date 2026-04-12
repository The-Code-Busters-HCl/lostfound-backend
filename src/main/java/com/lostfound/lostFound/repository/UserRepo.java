package com.lostfound.lostFound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lostfound.lostFound.model.*;

import java.util.Optional;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
        // Required for login
    Optional<User> findByEmail(String email);

    // Optional: if using phone login
    Optional<User> findByMobileNo(String mobileNo);

    // Search users by name (LIKE %name%)
    List<User> findByNameContaining(String name);

    // Filter users by branch
    List<User> findByBranch(String branch);

    // Filter users by year
    List<User> findByYear(int year);

    // Combined filter
    List<User> findByBranchAndYear(String branch, int year);
}
