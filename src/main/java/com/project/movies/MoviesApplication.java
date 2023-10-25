package com.project.movies;

import com.project.movies.service.FilmeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class MoviesApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(MoviesApplication.class, args);
	}

}
