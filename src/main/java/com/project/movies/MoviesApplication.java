package com.project.movies;

import com.project.movies.service.FilmeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(MoviesApplication.class, args);
		/*FilmeService service = new FilmeService();
		System.out.println(service.buscarListaFilmes(300));*/

	}

}
