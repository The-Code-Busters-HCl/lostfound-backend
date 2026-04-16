package com.lostfound.lostFound.service.impl;

import com.lostfound.lostFound.dto.UserDto;
import com.lostfound.lostFound.model.User;
import com.lostfound.lostFound.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.lostfound.lostFound.service.CustomUserDetailsService;

import java.util.Collections;


@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList());
    }

    public UserDto loadUserByUserId(Long id) {
        User user = userRepo.findById(id).orElse(null);

        if (user == null) throw new UsernameNotFoundException("User not present");
        return new UserDto(user.getName(), user.getBranch(), user.getMobileNo(), user.getRole()); 
    }
}