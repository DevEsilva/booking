package com.bideafactory.codingtest.Excepciones;

import com.bideafactory.codingtest.Dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleInvalidRequest(MethodArgumentNotValidException ex) {
        String description = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce("", (message1, message2) -> message1 + "; " + message2);
        Response Response = new Response(HttpStatus.BAD_REQUEST.value(), " Request Invalido", description);
        logger.error("Request Invalido: {}", description);
        return ResponseEntity.badRequest().body(Response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
            Response errorResponse = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrio un error: /*", ex.getMessage());
        logger.error("Ocurrio un error: ", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
