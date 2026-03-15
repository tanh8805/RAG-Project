package com.example.RAG_be.dto.Error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.io.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
  private String message;
  private int status;
}
