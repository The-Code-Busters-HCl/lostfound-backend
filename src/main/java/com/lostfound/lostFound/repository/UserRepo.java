package com.lostfound.lostFound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lostfound.lostFound.model.*;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);
}
