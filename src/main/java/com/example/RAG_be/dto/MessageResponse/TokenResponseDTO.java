package com.example.RAG_be.dto.MessageResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDTO {
  private String token;
  private int status;
}

