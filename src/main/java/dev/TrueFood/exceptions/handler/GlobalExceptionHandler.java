package dev.TrueFood.exceptions.handler;

import dev.TrueFood.exceptions.ErrorResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Not found",
                e);
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, Throwable throwable) {

        ErrorResponse errorResponse = new ErrorResponse(
                status.getReasonPhrase(),
                message,
                throwable.getMessage()
        );

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }





}
