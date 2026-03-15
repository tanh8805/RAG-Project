package com.example.RAG_be.service;

import com.example.RAG_be.entity.user.User;
import com.example.RAG_be.exception.UserExistsException;
import com.example.RAG_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.io.*;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }
  public User saveUser(String username, String password_hash) {
    if (userRepository.existsByUsername(username)) {
      throw new UserExistsException("Tài khoản '" + username + "' đã tồn tại!");
    }
    User user = User.builder().username(username).passwordHash(password_hash).build();
    userRepository.save(user);
    return user;
  }
}
