package com.project.movies.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductionCompaniesDTO {
    private String id;
    private String logo_path;
    private String name;
    private String origin_country;
}
