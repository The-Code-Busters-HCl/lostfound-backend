package com.lostfound.lostFound.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lostfound.lostFound.dto.UserDto;

public interface CustomUserDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);

    UserDto loadUserByUserId(Long id);


}
