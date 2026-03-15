package com.example.RAG_be.exception;

import lombok.Getter;

import java.util.*;
import java.io.*;

@Getter
public class AppException extends RuntimeException {
  private final int status;

  public AppException(int status, String message) {
    super(message);
    this.status = status;
  }
}
