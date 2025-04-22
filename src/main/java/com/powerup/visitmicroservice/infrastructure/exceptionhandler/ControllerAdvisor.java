package com.powerup.visitmicroservice.infrastructure.exceptionhandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.powerup.visitmicroservice.domain.exceptions.DuplicateVisitException;
import com.powerup.visitmicroservice.application.exceptions.InvalidUserAccesException;
import com.powerup.visitmicroservice.domain.exceptions.MaximumBuyersReachedException;
import com.powerup.visitmicroservice.domain.exceptions.TimeSlotConflictException;
import com.powerup.visitmicroservice.infrastructure.exceptions.UserAuthenticationErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(TimeSlotConflictException.class)
    ResponseEntity<ExceptionResponse> handleTimeSlotConflictException(TimeSlotConflictException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidUserAccesException.class)
    ResponseEntity<ExceptionResponse> handleInvalidUserAccesException(InvalidUserAccesException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MaximumBuyersReachedException.class)
    ResponseEntity<ExceptionResponse> handleMaximumBuyersReachedException(MaximumBuyersReachedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(DuplicateVisitException.class)
    ResponseEntity<ExceptionResponse> handleDuplicateVisitException(DuplicateVisitException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserAuthenticationErrorException.class)
    ResponseEntity<ExceptionResponse> handleUserAuthenticationErrorException(UserAuthenticationErrorException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }
    
    @ExceptionHandler(JWTVerificationException.class)
    ResponseEntity<ExceptionResponse> handleJWTVerificationException(JWTVerificationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }
}
