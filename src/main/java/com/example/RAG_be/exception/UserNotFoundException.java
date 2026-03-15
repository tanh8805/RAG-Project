package com.example.RAG_be.exception;

import java.util.*;
import java.io.*;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String message){
    super(message);
  }
}
