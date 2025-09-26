package com.quocchung.webbando.bandocongnghe.service.Impl;

import com.quocchung.webbando.bandocongnghe.config.JwtTokenProvider;
import com.quocchung.webbando.bandocongnghe.dto.request.RegisterRequest;
import com.quocchung.webbando.bandocongnghe.dto.response.LoginResponse;
import com.quocchung.webbando.bandocongnghe.dto.response.RegisterResponse;
import com.quocchung.webbando.bandocongnghe.entity.Role;
import com.quocchung.webbando.bandocongnghe.entity.User;
import com.quocchung.webbando.bandocongnghe.repository.RoleRepository;
import com.quocchung.webbando.bandocongnghe.repository.UserRepository;
import com.quocchung.webbando.bandocongnghe.service.AuthService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public RegisterResponse register(RegisterRequest registerRequest) {
    /* Email đã được dùng  */
    if(userRepository.existsByEmail(registerRequest.getEmail())){
       throw new IllegalArgumentException("Email đã tồn tại");
    }

    Role userRole = roleRepository.findByCode("USER").orElseThrow(() -> new IllegalArgumentException("ROLE USER chưa được seed"));
    User user = User.builder()
        .email(registerRequest.getEmail())
        .username(registerRequest.getUsername())
        .passwordHard(passwordEncoder.encode(registerRequest.getPassword()))
        .emailVarifiedAt(null)
        .roles(Set.of(userRole))
        .build();
    userRepository.save(user);

    String token = jwtTokenProvider.generateToken(user);

    return RegisterResponse.builder()
        .accessToken(token)
        .tokenType("Bearer")
        .userId(user.getId())
        .email(user.getEmail())
        .password(user.getPasswordHard())
        .emailVerificationRequired(true)
        .build();
  }

  @Override
  public LoginResponse login(RegisterRequest registerRequest) {
    return null;
  }
}
