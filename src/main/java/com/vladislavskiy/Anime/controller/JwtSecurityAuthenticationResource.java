package com.vladislavskiy.Anime.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class JwtSecurityAuthenticationResource {
    private JwtEncoder jwtEncoder;

    public JwtSecurityAuthenticationResource(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }
    @PostMapping("/authenticate")
    public JwtResponce authenticate(Authentication authentication){
        return new JwtResponce(createTocken(authentication));
    }

    private String createTocken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*30))
                .subject(authentication.getName())
                .claim("scope",createScope(authentication))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(a->a.getAuthority())
                .collect(Collectors.joining(" "));
    }
}
record JwtResponce(String token){}