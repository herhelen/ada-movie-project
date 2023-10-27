package com.project.movies.response;

import lombok.Data;

@Data
public class GenericResponse {

    private int status;
    private Object data;
    private String message;

}

