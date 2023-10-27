package com.project.movies.controller;

import com.project.movies.model.Filme;
import com.project.movies.model.Genero;
import com.project.movies.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FilmeControllerDoc {

    @Operation(summary = "Busca de filme por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Filme.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao buscar o filme por ID!",
                    content = @Content)
    })
    ResponseEntity<Filme> buscarFilmePorId(@PathVariable("id") Long id);

    @Operation(summary = "Busca de filmes por nome", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso!", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Filme.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Filmes não encontrados!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao buscar filmes por nome!",
                    content = @Content)
    })
    ResponseEntity<List<Filme>> buscarFilmePorNome(@PathVariable("nome") String nome);

    @Operation(summary = "Busca de filmes por ID do gênero", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso!", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Filme.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Filmes não encontrados!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao buscar filmes por ID do gênero!",
                    content = @Content)
    })
    ResponseEntity<List<Filme>> buscarFilmesPorGeneroId(@PathVariable("id") Long id);

    @Operation(summary = "Lista os gêneros de filmes", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gêneros listados com sucesso!", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Gêneros não encontrados!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro ao listar os gêneros!",
                    content = @Content)
    })
    ResponseEntity<List<Genero>> buscarGeneros();
}
