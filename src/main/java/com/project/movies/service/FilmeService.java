package com.project.movies.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.movies.dto.FilmeDTO;
import java.net.http.HttpRequest;

import com.project.movies.dto.GeneroDTO;
import com.project.movies.model.Filme;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class FilmeService {

    public Filme buscarListaFilmes(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/" + id + "?language=en-US"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDU4ZWMwZGQyYWFlYTFmODljZjgwNTExODBhZDQ5MyIsInN1YiI6IjY1MzZlZDA4NDFhYWM0MDBjMzMxYjIwOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JFb9Oi_3pG4mJuLt1EFtxbCOaC_ZgvIXldFRPM6w_gw")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Agora, você precisa converter a resposta para FilmeDTO
        ObjectMapper objectMapper = new ObjectMapper(); // Certifique-se de ter a biblioteca Jackson ObjectMapper no seu classpath
        FilmeDTO filmedto = objectMapper.readValue(response.body(), FilmeDTO.class);

        Filme filme = new Filme();
        filme.setNome(filmedto.getTitle());
        filme.setData(filmedto.getRelease_date());
        filme.setDescricao(filmedto.getOverview());
        filme.setDuracao(filmedto.getRuntime());
        for (GeneroDTO genero:filmedto.getGenres()) {
            filme.getGeneros().add(genero.getName());
        }
        filme.setPoster(filmedto.getPoster_path());

        return filme;
    }

}
