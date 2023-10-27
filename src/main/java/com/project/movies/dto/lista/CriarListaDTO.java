package com.project.movies.dto.lista;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CriarListaDTO {
    private String nome;
    private String descricao;
    private String privada;
}

