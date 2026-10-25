package kz.alibek.marketplace.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler()
    public ErrorResponse error (DataIntegrityViolationException ex){
        return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    }
}

