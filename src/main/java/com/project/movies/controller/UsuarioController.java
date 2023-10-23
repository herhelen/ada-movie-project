package com.project.movies.controller;

import com.project.movies.model.Usuario;
import com.project.movies.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movie-api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> buscarTodosOsContatos() {
        try {
            return usuarioService.listarTodosOsUsuarios();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar todos os usuarios");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarContatoPorId(@PathVariable("id") Long id) {
        try {
            return usuarioService.buscarPorId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario com ID " + id + " não encontrado");
        }
    }

    @GetMapping("/apelido/{apelido}")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> buscarContatosPorNome(@PathVariable("apelido") String apelido) {
        try {
            return usuarioService.buscarPorApelido(apelido);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar contatos pelo apelido " + apelido);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarContato(@RequestBody Usuario usuario) {
        try {
            return usuarioService.criarOuAtualizarUsuario(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar contato");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizarContato(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
        try {
            return usuarioService.atualizarUsuario(id, usuarioAtualizado);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar usuario com ID " + id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarContato(@PathVariable("id") Long id) {
        try {
            usuarioService.deletarUsuario(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar usuario com ID " + id);
        }
    }
}
