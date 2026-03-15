package com.example.RAG_be.controller;

import com.example.RAG_be.config.jwt.JwtService;
import com.example.RAG_be.config.security.CustomerUserDetails;
import com.example.RAG_be.dto.Login.LoginDTO;
import com.example.RAG_be.dto.MessageResponse.MessageResponseDTO;
import com.example.RAG_be.dto.MessageResponse.TokenResponseDTO;
import com.example.RAG_be.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
    try{
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      CustomerUserDetails principal = (CustomerUserDetails) authentication.getPrincipal();
      String token = jwtService.generateJwt(principal);
      return ResponseEntity.status(200).body(TokenResponseDTO.builder().token(token).status(200).build());
    }
    catch (Exception e){
      return ResponseEntity.status(500).body(MessageResponseDTO.builder().message(e.getMessage()).status(500).build());
    }
  }
}

