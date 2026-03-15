package com.example.RAG_be.dto.Register;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.*;
import java.io.*;

@Data
public class RegisterDTO {
  @NotBlank(message = "Tài khoản không được để trống")
  private String username;
  @NotBlank(message = "Mật khẩu không được để trống")
  private String password;
}
