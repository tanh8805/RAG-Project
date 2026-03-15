package com.example.RAG_be.exception;

import java.util.*;
import java.io.*;

public class UserExistsException extends RuntimeException {
  public UserExistsException(String message) {
    super(message);
  }
}
