package dev.TrueFood.exceptions.handler;

import dev.TrueFood.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                "Not found",
                e);
    }

    @ExceptionHandler(SelfLikeException.class)
    public ResponseEntity<ErrorResponse> handleSelfLikeException(SelfLikeException e) {
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

    @ExceptionHandler(EmailIsAlreadyUse.class)
    public ResponseEntity<ErrorResponse> handleEmailIsAlreadyUse(EmailIsAlreadyUse e) {
        return buildResponse(
                HttpStatus.CONFLICT,
                "This email is already in use",
                e);
    }

    @ExceptionHandler(UserAlreadyWorkerException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyWorkerException(UserAlreadyWorkerException e) {
        return buildResponse(
                HttpStatus.CONFLICT,
                "This user is already worker",
                e);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ErrorResponse> handlePermissionDeniedException(PermissionDeniedException e) {
        return buildResponse(
                HttpStatus.FORBIDDEN,
                "permission denied",
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
