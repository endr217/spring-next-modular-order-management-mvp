package com.example.ordermanagement.modules.auth.service;

import com.example.ordermanagement.modules.auth.api.response.UserLoginResponse;
import com.example.ordermanagement.modules.users.domain.User;
import com.example.ordermanagement.security.JwtProperties;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private final JwtEncoder jwtEncoder;
  private final Duration expiration;

  public TokenService(JwtEncoder jwtEncoder, JwtProperties properties) {
    this.jwtEncoder = jwtEncoder;
    this.expiration = Duration.ofMinutes(properties.expirationMinutes());
  }

  public UserLoginResponse issue(User user) {
    Instant now = Instant.now();
    List<String> roles =
        user.getUserRoles().stream().map(userRole -> userRole.getRole().getName()).toList();

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .subject(user.getUsername())
            .issuedAt(now)
            .expiresAt(now.plus(expiration))
            .claim("roles", roles)
            .build();

    JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

    String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

    return new UserLoginResponse(token, "Bearer", expiration.toSeconds());
  }
}
