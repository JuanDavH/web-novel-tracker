package com.github.juandavh.webnoveltracker.exception;

import com.github.juandavh.webnoveltracker.novel.NovelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NovelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNovelNotFoundException(NovelNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, exception.getMessage()));
    }
}
