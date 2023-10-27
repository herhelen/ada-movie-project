package com.project.movies.controller;

import com.project.movies.model.Usuario;
import com.project.movies.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsuarioControllerDoc {

    @Operation(summary = "Busca de todos os usuários", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor ao buscar todos os usuários!",
                    content = @Content)
    })
    List<Usuario> buscarTodosOsContatos();

    @Operation(summary = "Busca de usuário por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor ao buscar o usuário por ID!",
                    content = @Content)
    })
    Usuario buscarContatoPorId(@PathVariable("id") Long id);

    @Operation(summary = "Busca de usuário por nome", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor ao buscar o usuário por nome!",
                    content = @Content)
    })
    List<Usuario> buscarContatosPorNome(@PathVariable("apelido") String apelido);

    @Operation(summary = "Criar um novo usuário", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Algum dado inválido", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor ao criar um usuário novo!",
                    content = @Content)
    })
    ResponseEntity<GenericResponse> criarContato(@RequestBody Usuario usuario);

    @Operation(summary = "Atualizar usuário por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Algum dado inválido", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor ao atualizar o usuário!",
                    content = @Content)
    })
    ResponseEntity<GenericResponse> atualizarContato(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuarioAtualizado);

    @Operation(summary = "Excluir um usuário por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso!", content = @Content),
            @ApiResponse(responseCode = "401", description = "Usuário não logado!", content = @Content),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado!", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = @Content),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor ao excluir o usuário!",
                    content = @Content)
    })
    void deletarContato(@PathVariable("id") Long id);

}
