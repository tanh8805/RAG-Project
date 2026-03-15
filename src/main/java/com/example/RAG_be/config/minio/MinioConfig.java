package com.example.RAG_be.config.minio;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.io.*;

@Configuration
public class MinioConfig {
  @Bean
  public MinioClient minioClient() {
    return MinioClient.builder()
            .endpoint("http://localhost:9000")
            .credentials("minioadmin", "minioadmin")
            .build();
  }
}
