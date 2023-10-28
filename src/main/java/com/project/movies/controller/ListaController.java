package com.project.movies.controller;

import com.project.movies.dto.lista.CriarListaDTO;
import com.project.movies.dto.lista.ListaResponseDTO;
import com.project.movies.dto.lista.ListaRespostaDTO;
import com.project.movies.dto.lista.PaginaRespostaDTO;
import com.project.movies.model.Filme;
import com.project.movies.service.ListaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("movie-api/v1/listas")
public class ListaController {

    ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PaginaRespostaDTO<ListaRespostaDTO>> buscarListasDoUsuario(
            @PathVariable Long usuarioId,
            Pageable pageable) {
        PaginaRespostaDTO<ListaRespostaDTO> listas = listaService.buscarListasDoUsuario(usuarioId, pageable);
        return ResponseEntity.ok(listas);
    }

    @PostMapping("/usuario/{usuarioId}/lista")
    public ResponseEntity<ListaResponseDTO> criarLista(
            @PathVariable Long usuarioId,
            @RequestBody CriarListaDTO criarListaDTO) {
        ListaResponseDTO listaCriada = listaService.criarLista(usuarioId, criarListaDTO);
        return new ResponseEntity<>(listaCriada, HttpStatus.CREATED);
    }

    @PostMapping("/usuario/{usuarioId}/lista/{listaId}/adicionar-filme/{filmeId}")
    public ResponseEntity<ListaResponseDTO> adicionarFilme(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId,
            @PathVariable Long filmeId) {
        ListaResponseDTO listaAtualizada = listaService.adicionarFilmeALista(usuarioId, listaId, filmeId);
        return new ResponseEntity<>(listaAtualizada, HttpStatus.OK);
    }

    @PutMapping("/usuario/{usuarioId}/lista/{listaId}")
    public ResponseEntity<ListaResponseDTO> modificarLista(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId,
            @RequestBody CriarListaDTO modificarListaDTO) {
        ListaResponseDTO listaAtualizada = listaService.modificarLista(usuarioId, listaId, modificarListaDTO);
        return new ResponseEntity<>(listaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{usuarioId}/lista/{listaId}/remover-filme/{filmeId}")
    public ResponseEntity<ListaResponseDTO> removerFilme(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId,
            @PathVariable Long filmeId) {
        ListaResponseDTO listaAtualizada = listaService.removerFilmeDaLista(usuarioId, listaId, filmeId);
        return new ResponseEntity<>(listaAtualizada, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}/lista/{listaId}/itens")
    public ResponseEntity<List<Filme>> listarItensDaLista(
            @PathVariable Long usuarioId,
            @PathVariable Long listaId) {
        List<Filme> filmes = listaService.listarItensDaLista(usuarioId, listaId);
        return ResponseEntity.ok(filmes);
    }

}

