package com.example.RAG_be.repository;

import com.example.RAG_be.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User,UUID> {
  Optional<User> findByUsername(String username);
}
