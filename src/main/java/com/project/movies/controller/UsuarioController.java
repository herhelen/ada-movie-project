package com.project.movies.controller;

import com.project.movies.exception.CustomConstraintExceptionHandler;
import com.project.movies.model.Usuario;
import com.project.movies.response.GenericResponse;
import com.project.movies.service.UsuarioService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movie-api/v1/usuarios")
public class UsuarioController implements UsuarioControllerDoc {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> buscarTodosOsUsuarios() {
        try {
            return usuarioService.listarTodosOsUsuarios();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar todos os usuários");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPorId(@PathVariable("id") Long id) {
        try {
            return usuarioService.buscarPorId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com ID " + id + " não encontrado");
        }
    }

    @GetMapping("/apelido/{apelido}")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> buscarUsuariosPorNome(@PathVariable("apelido") String apelido) {
        try {
            return usuarioService.buscarPorApelido(apelido);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar usuário pelo apelido " + apelido);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenericResponse> criarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioGravado = usuarioService.criarOuAtualizarUsuario(usuario);

            if (usuarioGravado != null) {
                GenericResponse response = new GenericResponse();
                response.setStatus(HttpStatus.CREATED.value());
                response.setData(usuarioGravado);
                response.setMessage("Usuário criado com sucesso!");

                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (ConstraintViolationException exception) {
            CustomConstraintExceptionHandler ex = new CustomConstraintExceptionHandler();
            return new ResponseEntity<>(ex.processException(exception), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar usuário");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenericResponse> atualizarUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
        try {
            Usuario usuarioGravado = usuarioService.atualizarUsuario(id, usuarioAtualizado);

            if (usuarioGravado != null) {
                GenericResponse response = new GenericResponse();
                response.setStatus(HttpStatus.OK.value());
                response.setData(usuarioGravado);
                response.setMessage("Usuário atualizado com sucesso!");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ConstraintViolationException exception) {
            CustomConstraintExceptionHandler ex = new CustomConstraintExceptionHandler();
            return new ResponseEntity<>(ex.processException(exception), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar usuário com ID " + id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable("id") Long id) {
        try {
            usuarioService.deletarUsuario(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar usuário com ID " + id);
        }
    }
}
