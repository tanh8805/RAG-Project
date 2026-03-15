package com.example.RAG_be.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

@Service
@RequiredArgsConstructor
public class MinioFileService {
  private final MinioClient minioClient;
  public String uploadFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
    String objectName = UUID.randomUUID() + "_" + file.getOriginalFilename();
    minioClient.putObject(
            PutObjectArgs.builder()
                    .bucket("files")
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
    );
    return "files/" + objectName;
  }
}
