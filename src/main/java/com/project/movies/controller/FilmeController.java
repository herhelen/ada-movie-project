package com.project.movies.controller;

import com.project.movies.model.Filme;
import com.project.movies.model.Genero;
import com.project.movies.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movie-api/v1/filmes")
public class FilmeController implements FilmeControllerDoc {

    FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(filmeService.buscarFilmePorId(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme com ID " + id + " não encontrado");
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Filme>> buscarFilmePorNome(@PathVariable("nome") String nome) {
        try {
            return new ResponseEntity<>(filmeService.buscarFilmePorNome(nome), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme com nome " + nome + " não encontrado");
        }
    }

    @GetMapping("/genero/id/{id}")
    public ResponseEntity<List<Filme>> buscarFilmesPorGeneroId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(filmeService.buscarFilmesPorGenero(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filmes com gênero com ID " + id + " não encontrados");
        }
    }

    @GetMapping("/genero")
    public ResponseEntity<List<Genero>> buscarGeneros() {
        try {
            return new ResponseEntity<>(filmeService.buscarListaGeneros(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista vazia");
        }
    }
}
