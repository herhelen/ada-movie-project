package com.project.movies.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SpokenLanguagesDTO {
    private String english_name;
    private String iso_639_1;
    private String name;
}
