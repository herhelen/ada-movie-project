package com.project.movies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ResponseDTO {
    private String page;
    private List<FilmeListagemDTO> results;
    private String total_pages;
    private String total_results;
}
