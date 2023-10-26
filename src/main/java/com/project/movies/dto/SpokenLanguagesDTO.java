package com.project.movies.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpokenLanguagesDTO {
    private String english_name;
    private String iso_639_1;
    private String name;
}
