package com.example.RAG_be.controller;

import com.example.RAG_be.dto.File.FileDTO;
import com.example.RAG_be.dto.MessageResponse.MessageResponseDTO;
import com.example.RAG_be.entity.document.Document;
import com.example.RAG_be.exception.UserNotFoundException;
import com.example.RAG_be.repository.DocumentRepository;
import com.example.RAG_be.service.DocumentService;
import com.example.RAG_be.service.MinioFileService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
  private final MinioFileService minioFileService;
  private  final DocumentService documentService;

  @PostMapping("/")
  public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, Authentication auth) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
    String userId = (String) auth.getPrincipal();
    if(userId == null){
      throw new UserNotFoundException("Lỗi người dùng chưa đăng nhập");
    }
    FileDTO fileDTO = minioFileService.uploadFile(file);
    String fileName = fileDTO.getFileName();
    String filePath = fileDTO.getFilePath();
    String fileType = fileDTO.getFileType();
    documentService.saveDocument(fileName, filePath, fileType, userId);
    return ResponseEntity.ok().body(MessageResponseDTO.builder().message("Upload thành công").build());
  }
}