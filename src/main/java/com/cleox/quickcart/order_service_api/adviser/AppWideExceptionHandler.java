package com.cleox.quickcart.order_service_api.adviser;

import com.cleox.quickcart.order_service_api.exception.EntryNotFoundException;
import com.cleox.quickcart.order_service_api.util.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponseDto> handleEntryNotFoundException(EntryNotFoundException e){
        return new ResponseEntity<>(
                new StandardResponseDto(
                        404,e.getMessage(),e
                ), HttpStatus.NOT_FOUND
        );
    }
}
