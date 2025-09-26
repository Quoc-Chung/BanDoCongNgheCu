package com.quocchung.webbando.bandocongnghe.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
  private String tokenType;
  private String accessToken;
  private String refreshToken;
  private LocalDateTime expiresAt;

  private Long userId;
  private String email;
  private String message;
  private Boolean emailVerificationRequired;

}

