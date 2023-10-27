package com.project.movies.dto.lista;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginaRespostaDTO<T> {
    private int pagina;
    private List<T> resultados;
    private int totalDePaginas;
    private long totalDeResultados;
    private String algumCampoAdicional;

}
