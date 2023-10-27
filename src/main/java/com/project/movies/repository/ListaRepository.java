package com.project.movies.repository;

import com.project.movies.model.Lista;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    Page<Lista> findByUsuarioId(Long usuarioId, Pageable pageable);

    Optional<Lista> findByIdAndUsuarioId(Long listaId, Long usuarioId);
}

