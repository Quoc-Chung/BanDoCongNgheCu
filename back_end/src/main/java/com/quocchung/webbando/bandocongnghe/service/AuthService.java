package com.quocchung.webbando.bandocongnghe.service;

import com.quocchung.webbando.bandocongnghe.dto.request.RegisterRequest;
import com.quocchung.webbando.bandocongnghe.dto.response.LoginResponse;
import com.quocchung.webbando.bandocongnghe.dto.response.RegisterResponse;

public interface AuthService {

      RegisterResponse register(RegisterRequest registerRequest);
      LoginResponse login(RegisterRequest registerRequest);
}
