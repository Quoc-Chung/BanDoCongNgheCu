package com.quocchung.webbando.bandocongnghe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "password_reset_otps")
public class PasswordResetOtp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String otpCode;
  private LocalDateTime expiresAt;
  private LocalDateTime createAt;
  private LocalDateTime usedAt;


  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
