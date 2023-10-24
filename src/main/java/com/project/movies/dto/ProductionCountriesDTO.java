package com.project.movies.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductionCountriesDTO {
    private String iso_3166_1;
    private String name;
}
