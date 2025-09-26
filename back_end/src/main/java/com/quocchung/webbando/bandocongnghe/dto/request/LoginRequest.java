package com.quocchung.webbando.bandocongnghe.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
  @NotBlank
  private String email;

  @NotBlank
  private String password;
}