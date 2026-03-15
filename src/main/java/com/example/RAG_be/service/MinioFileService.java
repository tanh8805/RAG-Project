package com.example.RAG_be.service;

import com.example.RAG_be.dto.File.FileDTO;
import com.example.RAG_be.exception.AppException;
import com.example.RAG_be.exception.ErrorFileFormat; // Lỗi custom của bạn
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioFileService {

  private final MinioClient minioClient;

  public FileDTO uploadFile(MultipartFile file) {
    String originalFilename = file.getOriginalFilename();
    String ext = "";

    if (originalFilename != null && originalFilename.lastIndexOf(".") > 0) {
      ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
    }
    if (!ext.equals("pdf") && !ext.equals("doc") && !ext.equals("docx")) {
      throw new ErrorFileFormat("Chỉ được gửi file định dạng PDF hoặc Word (.doc, .docx)");
    }
    try {
      String objectName = UUID.randomUUID() + "_" + originalFilename;

      minioClient.putObject(
              PutObjectArgs.builder()
                      .bucket("files")
                      .object(objectName)
                      .stream(file.getInputStream(), file.getSize(), -1)
                      .contentType(file.getContentType()) // Ví dụ: application/pdf
                      .build()
      );

      return FileDTO.builder()
              .fileName(file.getOriginalFilename())
              .fileType(ext)
              .filePath("files/" + objectName)
              .build();

    } catch (Exception e) {
      throw new AppException(500, "Lỗi máy chủ khi lưu file vào MinIO: " + e.getMessage());
    }
  }
}