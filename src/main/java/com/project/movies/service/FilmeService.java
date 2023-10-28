package com.project.movies.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.movies.dto.*;

import java.net.http.HttpRequest;

import com.project.movies.model.Filme;
import com.project.movies.model.Genero;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FilmeService {

    public Filme buscarFilmePorId(long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.themoviedb.org/3/movie/" + id + "?language=pt-BR"))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDU4ZWMwZGQyYWFlYTFmODljZjgwNTExODBhZDQ5MyIsInN1YiI6IjY1MzZlZDA4NDFhYWM0MDBjMzMxYjIwOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JFb9Oi_3pG4mJuLt1EFtxbCOaC_ZgvIXldFRPM6w_gw")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                FilmeDTO filmedto = objectMapper.readValue(response.body(), FilmeDTO.class);

                Filme filme = new Filme();
                filme.setId(Long.parseLong(filmedto.getId()));
                filme.setNome(filmedto.getTitle());
                filme.setDescricao(filmedto.getOverview());
                filme.setDuracao(filmedto.getRuntime());
                for (GeneroDTO genero : filmedto.getGenres()) {
                    filme.getGeneros().add(genero.getName());
                }
                filme.setPoster(filmedto.getPoster_path());
                return filme;
            } else {
                System.err.println("Filme não encontrado para o ID: " + id);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Filme> buscarFilmePorNome(String nome) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/search/movie?query=" + nome + "&include_adult=false&language=pt-BR&page=1"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDU4ZWMwZGQyYWFlYTFmODljZjgwNTExODBhZDQ5MyIsInN1YiI6IjY1MzZlZDA4NDFhYWM0MDBjMzMxYjIwOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JFb9Oi_3pG4mJuLt1EFtxbCOaC_ZgvIXldFRPM6w_gw")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = objectMapper.readValue(response.body(), ResponseDTO.class);

        System.out.println(responseDTO);
        List<Filme> filmes = new ArrayList<>();
        for (FilmeListagemDTO filmedto:responseDTO.getResults()) {
            Filme filme = new Filme();
            filme.setId(Long.parseLong(filmedto.getId()));
            filme.setNome(filmedto.getTitle());
            //filme.setData(filmedto.getRelease_date());
            filme.setDescricao(filmedto.getOverview());
            for (String genero:filmedto.getGenre_ids()) {
                filme.getGeneros().add(genero);
            }
            filme.setPoster(filmedto.getPoster_path());
            filmes.add(filme);
        }

        return filmes;
    }

    public List<Filme> buscarFilmesPorGenero(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/discover/movie?with_genres=" + id + "&language=pt-BR"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDU4ZWMwZGQyYWFlYTFmODljZjgwNTExODBhZDQ5MyIsInN1YiI6IjY1MzZlZDA4NDFhYWM0MDBjMzMxYjIwOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JFb9Oi_3pG4mJuLt1EFtxbCOaC_ZgvIXldFRPM6w_gw")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = objectMapper.readValue(response.body(), ResponseDTO.class);

        System.out.println(responseDTO);
        List<Filme> filmes = new ArrayList<>();
        for (FilmeListagemDTO filmedto:responseDTO.getResults()) {
            Filme filme = new Filme();
            filme.setId(Long.parseLong(filmedto.getId()));
            filme.setNome(filmedto.getTitle());
            //filme.setData(filmedto.getRelease_date());
            filme.setDescricao(filmedto.getOverview());
            for (String genero:filmedto.getGenre_ids()) {
                filme.getGeneros().add(genero);
            }
            filme.setPoster(filmedto.getPoster_path());
            filmes.add(filme);
        }

        return filmes;
    }

    public List<Genero> buscarListaGeneros() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/genre/movie/list?language=pt-BR"))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDU4ZWMwZGQyYWFlYTFmODljZjgwNTExODBhZDQ5MyIsInN1YiI6IjY1MzZlZDA4NDFhYWM0MDBjMzMxYjIwOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JFb9Oi_3pG4mJuLt1EFtxbCOaC_ZgvIXldFRPM6w_gw")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        GeneroListagemDTO generoListagemDTO = objectMapper.readValue(response.body(), GeneroListagemDTO.class);

        List<Genero> generos = new ArrayList<>();
        for (GeneroDTO generoDTO:generoListagemDTO.getGenres()) {
            Genero genero = new Genero();
            genero.setId(Long.parseLong(generoDTO.getId()));
            genero.setNome(generoDTO.getName());

            generos.add(genero);
        }

        return generos;
    }
}
