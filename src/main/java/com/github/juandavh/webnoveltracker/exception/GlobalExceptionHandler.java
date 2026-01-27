package com.github.juandavh.webnoveltracker.exception;

import com.github.juandavh.webnoveltracker.novelfolder.exception.InvalidFolderItemPositionException;
import com.github.juandavh.webnoveltracker.novelfolder.exception.NovelAlreadyInFolderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, exception.getMessage()));
    }

    @ExceptionHandler(InvalidFolderItemPositionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFolderItemPositionException(
            InvalidFolderItemPositionException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, exception.getMessage()));
    }

    @ExceptionHandler(NovelAlreadyInFolderException.class)
    public ResponseEntity<ErrorResponse> handleNovelAlreadyInFolderException(NovelAlreadyInFolderException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, exception.getMessage()));
    }
}
