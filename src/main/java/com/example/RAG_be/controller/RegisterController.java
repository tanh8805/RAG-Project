package com.example.RAG_be.controller;

import com.example.RAG_be.dto.MessageResponse.MessageResponseDTO;
import com.example.RAG_be.dto.Register.RegisterDTO;
import com.example.RAG_be.dto.Register.RegisterResponse;
import com.example.RAG_be.entity.user.User;
import com.example.RAG_be.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.io.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegisterController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
    String username = registerDTO.getUsername();
    String password_hash = passwordEncoder.encode(registerDTO.getPassword());
    User newUser = userService.saveUser(username,password_hash);
    return ResponseEntity.status(201).body(RegisterResponse.builder().username(username).role(newUser.getRole().toString()).build());
  }
}
