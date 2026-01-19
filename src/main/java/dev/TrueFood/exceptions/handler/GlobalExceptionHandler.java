package dev.TrueFood.exceptions.handler;

import dev.TrueFood.exceptions.ErrorResponse;
import dev.TrueFood.exceptions.FailedUploadException;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.exceptions.SelfLikeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Not found",
                e);
    }

    @ExceptionHandler(SelfLikeException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(SelfLikeException e) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Self like",
                e);
    }

    @ExceptionHandler(FailedUploadException.class)
    public ResponseEntity<ErrorResponse> handleFailedUploadException(FailedUploadException e) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Failed upload file",
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
