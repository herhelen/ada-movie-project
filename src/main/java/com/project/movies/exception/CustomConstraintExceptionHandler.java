package com.project.movies.exception;

import com.project.movies.response.GenericResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@Slf4j
public class CustomConstraintExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public GenericResponse processException(ConstraintViolationException e) {
        log.error(e.getMessage());
        Optional<ConstraintViolation<?>> ce = e.getConstraintViolations().stream().findFirst();
        if (ce.isPresent()) {
            String violationMessage = ce.get().getMessage();
            GenericResponse response = new GenericResponse();
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(violationMessage);
            return response;
        }
        GenericResponse response = new GenericResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    public GenericResponse processException(RuntimeException e) {
        log.error(e.getMessage());
        GenericResponse response = new GenericResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        return response;
    }

}
