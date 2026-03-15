package com.example.RAG_be.entity.document;

import com.example.RAG_be.entity.user.Role;
import com.example.RAG_be.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "documents")
public class Document {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_type")
  private String fileType;

  @Column(name = "file_path")
  private String filePath;

  @ManyToOne
  @JoinColumn(name = "uploaded_by")
  private User uploadedBy;

  @Enumerated(EnumType.STRING)
  private DocumentStatus status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    status = DocumentStatus.PENDING;
  }
}
