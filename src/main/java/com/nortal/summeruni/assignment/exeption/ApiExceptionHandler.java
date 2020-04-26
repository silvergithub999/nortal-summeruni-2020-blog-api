package com.nortal.summeruni.assignment.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UsernameTakenException.class})
    public ResponseEntity<Object> handleUsernameTaken(UsernameTakenException e) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiException exception = new ApiException(
                e.getMessage(),
                e,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, httpStatus);
    }
}
