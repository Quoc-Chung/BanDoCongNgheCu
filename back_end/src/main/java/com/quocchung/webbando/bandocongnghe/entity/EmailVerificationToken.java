package com.quocchung.webbando.bandocongnghe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "email_verification_tokens")
public class EmailVerificationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;
  private LocalDateTime expiresAt;
  private LocalDateTime usedAt;
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
