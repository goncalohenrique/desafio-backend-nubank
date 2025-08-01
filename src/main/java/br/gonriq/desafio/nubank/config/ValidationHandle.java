package br.gonriq.desafio.nubank.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandle {

    private final View error;

    //Esse método é chamado automaticamente quando uma MethodArgumentNotValidException ocorre
    public ValidationHandle(View error) {
        this.error = error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException exc) {

        Map<String, String> erros = new HashMap<>();
        exc.getBindingResult().getFieldErrors().forEach(error ->
            erros.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}
