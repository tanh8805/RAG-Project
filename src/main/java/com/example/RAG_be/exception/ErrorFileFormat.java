package com.example.RAG_be.exception;

import java.util.*;
import java.io.*;

public class ErrorFileFormat extends RuntimeException {
  public ErrorFileFormat(String message) {
      super(message);
  }
}
