package com.example.RAG_be.service;

import com.example.RAG_be.entity.document.Document;
import com.example.RAG_be.entity.user.User;
import com.example.RAG_be.exception.AppException;
import com.example.RAG_be.exception.UserNotFoundException;
import com.example.RAG_be.repository.DocumentRepository;
import com.example.RAG_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.util.*;
import java.io.*;

@Service
@RequiredArgsConstructor
public class DocumentService {
  private final DocumentRepository documentRepository;
  private final UserRepository userRepository;
  private final RestTemplate restTemplate;

  @Value("${minio.url}")
  private String minioUrl;

  @Value("${fastapi.url}")
  private String fastApiUrl;

  public void saveDocument(String fileName, String filePath, String fileType, String userId) {
    User user = userRepository.findById(UUID.fromString(userId))
            .orElseThrow(() -> new UserNotFoundException("Lỗi không tìm thấy user"));
    if (documentRepository.existsByFileNameAndUploadedBy(fileName, user)) {
      throw new AppException(409, "Bạn đã tải lên tài liệu có tên '" + fileName + "' rồi!");
    }
    Document document = Document.builder().fileName(fileName).filePath(filePath).fileType(fileType).uploadedBy(user).build();
    Document saved = documentRepository.save(document);
    String fileUrl = minioUrl + "/" + filePath;
    sendToFastApi(saved.getId().toString(), fileUrl, fileName);
  }
  private void sendToFastApi(String documentId, String fileUrl, String fileName) {
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("document_id", documentId);
    body.add("file_url", fileUrl);
    body.add("file_name", fileName);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    restTemplate.postForEntity(
            fastApiUrl + "/api/process-document",
            new HttpEntity<>(body, headers),
            String.class
    );
  }
}
