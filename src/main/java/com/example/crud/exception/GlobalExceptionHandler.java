package com.example.crud.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.crud.exception.user.UserNotFoundException;
import com.example.crud.model.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(404, false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(IllegalArgumentException ex) {
        ApiResponse<String> response = new ApiResponse<>(400, false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>(500, false, "Internal Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiResponse<List<String>> response = new ApiResponse<>(400, false, "Validation failed", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
