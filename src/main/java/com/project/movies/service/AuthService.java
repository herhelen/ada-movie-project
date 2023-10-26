package com.project.movies.service;

import com.project.movies.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
private final AuthenticationManager authenticationManager;
private final JwtTokenProvider tokenProvider;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }
}
