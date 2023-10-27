package com.project.movies.dto.lista;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaResponseDTO {
    private boolean success;
    private int statusCode;
    private String statusMessage;
    private Long id;
}

