package com.project.movies.dto.lista;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaRespostaDTO {
    private String nome;
    private String posterPath;
    private String descricao;
    private int numeroDeItens;

}
