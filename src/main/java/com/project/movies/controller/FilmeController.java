package com.project.movies.controller;

import com.project.movies.dto.FilmeDTO;
import com.project.movies.model.Filme;
import com.project.movies.model.Usuario;
import com.project.movies.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/movie-api/v1/filmes")
public class FilmeController {

    FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(filmeService.buscarListaFilmes(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme com ID " + id + " não encontrado");
        }
    }
}
