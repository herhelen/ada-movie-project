package com.project.movies.controller;

import com.project.movies.dto.LoginDTO;
import com.project.movies.response.AuthResponse;
import com.project.movies.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-api/v1/login")
public class AuthController implements AuthControllerDoc {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
}
