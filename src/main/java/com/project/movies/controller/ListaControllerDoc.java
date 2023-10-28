package com.project.movies.controller;

import com.project.movies.dto.lista.CriarListaDTO;
import com.project.movies.dto.lista.ListaResponseDTO;
import com.project.movies.dto.lista.ListaRespostaDTO;
import com.project.movies.dto.lista.PaginaRespostaDTO;
import com.project.movies.model.Filme;
import com.project.movies.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ListaControllerDoc {


    @Operation(summary = "Busca de listas de filmes de um usuário",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listas encontradas com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PaginaRespostaDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<PaginaRespostaDTO<ListaRespostaDTO>> buscarListasDoUsuario(
            @PathVariable Long usuarioId,
            Pageable pageable);

    @Operation(summary = "Criar uma nova lista para um usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista criada com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListaResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<ListaResponseDTO> criarLista(
            @PathVariable Long usuarioId,
            @RequestBody CriarListaDTO criarListaDTO);

    @Operation(summary = "Adicionar um filme a uma lista de um usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filme adicionado com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListaResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<ListaResponseDTO> adicionarFilme(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId,
            @PathVariable Long filmeId);

    @Operation(summary = "Modificar uma lista de um usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lista modificada com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListaResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<ListaResponseDTO> modificarLista(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId,
            @RequestBody CriarListaDTO modificarListaDTO);

    @Operation(summary = "Remover um filme de uma lista de um usuário",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filme removido com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ListaResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<ListaResponseDTO> removerFilme(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId,
            @PathVariable Long filmeId);

    @Operation(summary = "Listar filmes de uma lista de um usuário",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filmes listados com sucesso!", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Filme.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content)
    })
    ResponseEntity<List<Filme>> listarItensDaLista(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId);
}
