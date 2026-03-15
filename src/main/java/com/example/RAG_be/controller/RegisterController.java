package com.example.RAG_be.controller;

import com.example.RAG_be.dto.MessageResponse.MessageResponseDTO;
import com.example.RAG_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.io.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegisterController {
  private final UserService userService;
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(String username, String password) {
    try{

    }
    catch (Exception e){
      return ResponseEntity.status(500).body(MessageResponseDTO.builder().message(e.getMessage()).status(500).build());
    }
  }
}
