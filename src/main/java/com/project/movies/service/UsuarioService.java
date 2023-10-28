package com.project.movies.service;

import com.project.movies.model.Usuario;
import com.project.movies.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder= passwordEncoder;

    }

    public List<Usuario> listarTodosOsUsuarios(){
        return this.usuarioRepository.findAll();
    }

    public Usuario criarOuAtualizarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return this.usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario não encontrado para deletar");
        }
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public List<Usuario> buscarPorApelido(String nome) {
        return usuarioRepository.findUsuarioByApelidoContainingIgnoreCase(nome);
    }

    public Usuario buscarPorEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByEmail(email);
        return usuario.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setApelido(usuarioAtualizado.getApelido());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado para atualizar");
        }
    }

}
