package com.project.movies.service;

import com.project.movies.dto.lista.CriarListaDTO;
import com.project.movies.dto.lista.ListaResponseDTO;
import com.project.movies.dto.lista.ListaRespostaDTO;
import com.project.movies.dto.lista.PaginaRespostaDTO;
import com.project.movies.model.Filme;
import com.project.movies.model.ItemLista;
import com.project.movies.model.Lista;
import com.project.movies.model.Usuario;
import com.project.movies.repository.ItemListaRepository;
import com.project.movies.repository.ListaRepository;
import com.project.movies.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaService {

    private final ListaRepository listaRepository;
    private final UsuarioRepository usuarioRepository;

    private final ItemListaRepository itemListaRepository;

    private final FilmeService filmeService;

    public ListaService(ListaRepository listaRepository, UsuarioRepository usuarioRepository,
                        ItemListaRepository itemListaRepository, FilmeService filmeService) {
        this.listaRepository = listaRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemListaRepository = itemListaRepository;
        this.filmeService = filmeService;
    }

    public PaginaRespostaDTO<ListaRespostaDTO> buscarListasDoUsuario(Long usuarioId, Pageable pageable) {
        Page<Lista> paginaDeListas = listaRepository.findByUsuarioId(usuarioId, pageable);

        List<ListaRespostaDTO> listaResposta = paginaDeListas.getContent().stream()
                .map(lista -> new ListaRespostaDTO(
                        lista.getNome(),
                        lista.getPosterPath(),
                        lista.getDescricao(),
                        lista.getItens().size())
                )
                .collect(Collectors.toList());

        PaginaRespostaDTO<ListaRespostaDTO> resposta = new PaginaRespostaDTO<>();
        resposta.setPagina(paginaDeListas.getNumber());
        resposta.setResultados(listaResposta);
        resposta.setTotalDePaginas(paginaDeListas.getTotalPages());
        resposta.setTotalDeResultados(paginaDeListas.getTotalElements());

        resposta.setAlgumCampoAdicional("valor adicional");

        return resposta;
    }
    public ListaResponseDTO criarLista(Long usuarioId, CriarListaDTO criarListaDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId)
        );

        Lista lista = new Lista();
        lista.setUsuario(usuario);
        lista.setNome(criarListaDTO.getNome());
        lista.setDescricao(criarListaDTO.getDescricao());

        Lista listaSalva = listaRepository.save(lista);

        ListaResponseDTO respostaDTO = new ListaResponseDTO();
        respostaDTO.setSuccess(true);
        respostaDTO.setStatusCode(1);
        respostaDTO.setStatusMessage("Success.");
        respostaDTO.setId(listaSalva.getId());

        return respostaDTO;
    }


    public ListaResponseDTO adicionarFilmeALista(Long usuarioId, Long listaId, Long filmeId) {
        Lista lista = listaRepository.findByIdAndUsuarioId(listaId, usuarioId)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada ou você não tem permissão para acessar esta lista."));

        ItemLista itemLista = new ItemLista();
        itemLista.setLista(lista);
        itemLista.setIdFilme(filmeId);

        itemLista = itemListaRepository.save(itemLista);
        lista = listaRepository.save(lista);

        ListaResponseDTO respostaDTO = new ListaResponseDTO();
        respostaDTO.setSuccess(true);
        respostaDTO.setStatusCode(1);
        respostaDTO.setStatusMessage("Filme adicionado com sucesso à lista.");
        respostaDTO.setId(lista.getId());

        return respostaDTO;
    }

    public ListaResponseDTO modificarLista(Long usuarioId, Long listaId, CriarListaDTO modificarListaDTO) {
        Lista lista = listaRepository.findByIdAndUsuarioId(listaId, usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada ou você não tem permissão para acessá-la"));

        if (modificarListaDTO.getNome() != null) {
            lista.setNome(modificarListaDTO.getNome());
        }
        if (modificarListaDTO.getDescricao() != null) {
            lista.setDescricao(modificarListaDTO.getDescricao());
        }

        lista = listaRepository.save(lista);

        ListaResponseDTO respostaDTO = new ListaResponseDTO();
        respostaDTO.setSuccess(true);
        respostaDTO.setStatusCode(1);
        respostaDTO.setStatusMessage("Lista modificada.");
        respostaDTO.setId(lista.getId());

        return respostaDTO;
    }

    public ListaResponseDTO removerFilmeDaLista(Long usuarioId, Long listaId, Long filmeId) {
        Lista lista = listaRepository.findByIdAndUsuarioId(listaId, usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada ou você não tem permissão para acessá-la"));

        ItemLista itemParaRemover = lista.getItens().stream()
                .filter(item -> filmeId.equals(item.getIdFilme()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado na lista"));

        lista.getItens().remove(itemParaRemover);
        itemListaRepository.delete(itemParaRemover);
        lista = listaRepository.save(lista);

        ListaResponseDTO respostaDTO = new ListaResponseDTO();
        respostaDTO.setSuccess(true);
        respostaDTO.setStatusCode(1);
        respostaDTO.setStatusMessage("Filme removido com sucesso da lista.");
        respostaDTO.setId(lista.getId());

        return respostaDTO;
    }

    public List<Filme> listarItensDaLista(Long usuarioId, Long listaId) {
        Lista lista = listaRepository.findByIdAndUsuarioId(listaId, usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada ou você não tem permissão para acessá-la"));

        return lista.getItens().stream()
                .map(itemLista -> {
                    Filme filme = filmeService.buscarFilmePorId(itemLista.getIdFilme());
                    if (filme != null) {
                        return filme;
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
                    }
                })
                .collect(Collectors.toList());
    }



}

