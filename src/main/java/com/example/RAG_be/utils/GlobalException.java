package com.example.RAG_be.utils;

import com.example.RAG_be.dto.Error.ErrorResponse;
import com.example.RAG_be.exception.AppException;
import com.example.RAG_be.exception.ErrorFileFormat;
import com.example.RAG_be.exception.UserExistsException;
import com.example.RAG_be.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalException {
  @ExceptionHandler(UserExistsException.class)
  public ResponseEntity<?> handleUserExistsException(UserExistsException ex) {
    return ResponseEntity.status(409).body(ErrorResponse.builder().message(ex.getMessage()).status(409).build());
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<?> handleAppException(AppException ex) {
    return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.builder().message(ex.getMessage()).status(ex.getStatus()).build());
  }

  @ExceptionHandler(ErrorFileFormat.class)
  public ResponseEntity<?> handleErrorFileFormat(ErrorFileFormat ex) {
    return ResponseEntity.status(400).body(ErrorResponse.builder().message(ex.getMessage()).status(400).build());
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
    return ResponseEntity.status(401).body(ErrorResponse.builder().message(ex.getMessage()).status(401).build());
  }
}
