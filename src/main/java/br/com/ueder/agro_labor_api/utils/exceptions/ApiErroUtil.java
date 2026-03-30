package br.com.ueder.agro_labor_api.utils.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiErroUtil {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handlerEntityNotFoundException(EntityNotFoundException e){
        ApiError error = new ApiError(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ApiValidationError>> handlerConstraintViolationException(ConstraintViolationException e){
        List<ApiValidationError> errors = e.getConstraintViolations().stream().map(x -> new ApiValidationError(x.getPropertyPath().toString(),
                x.getMessage(), LocalDateTime.now())).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<List<ApiValidationError>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ApiValidationError> errors = e.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(x -> new ApiValidationError(x.getField(), x.getDefaultMessage(), LocalDateTime.now()))
                    .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(RNException.class)
    public ResponseEntity<ApiError> handlerRNException(RNException e){
        ApiError error = new ApiError(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiError> handlerNumberFormatException(NumberFormatException e){
        ApiError error = new ApiError(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<ApiError> handlerRegistroDuplicadoException(RegistroDuplicadoException e){
        ApiError error = new ApiError(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e){
        ApiError apiError = new ApiError(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerException(Exception e){
        ApiError error = new ApiError(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
