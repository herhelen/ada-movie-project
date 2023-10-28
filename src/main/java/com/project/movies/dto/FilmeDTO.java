package com.project.movies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class FilmeDTO {
    private String adult;
    private String backdrop_path;
    private String budget;
    private List<GeneroDTO> genres; // id e name
    private String homepage;
    private String id;
    private String original_language;
    private String original_title;
    private String overview;
    private String popularity;
    private String poster_path;
    private List<ProductionCompaniesDTO> production_companies; //id, logo_path, name e origin_country
    private List<ProductionCountriesDTO> production_countries; //iso_3166_1 e name
    private String release_date;
    private String revenue;
    private String runtime;
    private List<SpokenLanguagesDTO> spoken_languages; //english_name, iso_639_1 e name
    private String status;
    private String tagline;
    private String title;
    private String video;
    private String vote_average;
    private String vote_count;
}
