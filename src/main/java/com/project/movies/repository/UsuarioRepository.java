package com.project.movies.repository;

import com.project.movies.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findUsuarioByApelidoContainingIgnoreCase(String apelido);

    Optional<Usuario> findUsuarioByEmail(String email);
}
