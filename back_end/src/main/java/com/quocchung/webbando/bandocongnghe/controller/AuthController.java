package com.quocchung.webbando.bandocongnghe.controller;

import com.quocchung.webbando.bandocongnghe.dto.request.LoginRequest;
import com.quocchung.webbando.bandocongnghe.dto.request.RegisterRequest;
import com.quocchung.webbando.bandocongnghe.dto.response.LoginResponse;
import com.quocchung.webbando.bandocongnghe.dto.response.RegisterResponse;
import com.quocchung.webbando.bandocongnghe.dto.response.generals.GeneralResponse;
import com.quocchung.webbando.bandocongnghe.dto.response.generals.ResponseFactory;
import com.quocchung.webbando.bandocongnghe.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<GeneralResponse<RegisterRequest>> register(
      @RequestBody @Valid RegisterRequest registerRequest){
    RegisterResponse response = authService.register(registerRequest);
    return ResponseFactory.success(response);
  }

  @PostMapping("/login")
  public ResponseEntity<GeneralResponse<LoginResponse>> login(
      @RequestBody @Valid LoginRequest loginRequest) {
    LoginResponse response = authService.login(loginRequest);
    return ResponseFactory.success(response);
  }




}
