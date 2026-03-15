package com.example.RAG_be.repository;

import com.example.RAG_be.entity.document.Document;
import com.example.RAG_be.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.io.*;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
  List<Document> findByFileName(String fileName);
  List<Document> findByFilePath(String filePath);
  boolean existsByFileNameAndUploadedBy(String fileName, User uploadedBy);
}
