package com.example.RAG_be.dto.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.io.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
  private String fileName;
  private String fileType;
  private String filePath;
}
