package com.example.RAG_be.config.security;

import com.example.RAG_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.io.*;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
            .map((user) -> new CustomerUserDetails(user))
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
