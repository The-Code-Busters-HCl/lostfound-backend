package com.lostfound.lostFound.controller;

import com.lostfound.lostFound.dto.UserDto;
import com.lostfound.lostFound.model.User;
import com.lostfound.lostFound.repository.UserRepo;
import com.lostfound.lostFound.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered";
    }

    @GetMapping("/userdetails/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Long id) {
        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDto(null, null, null, null));
        }

        return ResponseEntity.ok(UserDto.fromEntity(user));
    }
    

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        User dbUser = userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of("token", token);
    }
}
