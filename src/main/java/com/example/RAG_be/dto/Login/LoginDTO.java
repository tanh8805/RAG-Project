package com.example.RAG_be.dto.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
  @NotBlank(message = "Tài khoản không được để trống")
  private String username;
  @NotBlank(message = "Mật khẩu không được để trống")
  private String password;
}