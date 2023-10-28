package com.project.movies.controller;

import com.project.movies.dto.LoginDTO;
import com.project.movies.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDoc {


    @Operation(summary = "Realizar autenticação do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário logado com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
            }),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginDTO dto);
}
