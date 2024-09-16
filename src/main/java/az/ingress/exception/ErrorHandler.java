package az.ingress.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.exception.ErrorMessage.UNEXPECTED_ERROR;
import static az.ingress.exception.ErrorMessage.VALIDATION_ERROR;
import static az.ingress.util.ErrorUtil.ERROR_UTIL;
import static az.ingress.util.LocalizationUtil.LOCALIZATION_UTIL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handle(Exception ex) {
        log.error("Exception: ", ex);
        var message = LOCALIZATION_UTIL.getMessageByKey(UNEXPECTED_ERROR.getCode());
        return new ErrorResponse(message);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse handle(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException: ", ex);
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(BindException ex) {
        log.error("BindException: ", ex);

        var message = LOCALIZATION_UTIL.getMessageByKey(VALIDATION_ERROR.getCode());
        var validationErrors = ERROR_UTIL.extractValidationErrors(ex.getFieldErrors());

        return new ErrorResponse(message, validationErrors);
    }

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponse> handle(CustomFeignException ex) {
        log.error("CustomFeignException: ", ex);
        return ResponseEntity.status(ex.getHttpStatusCode())
                             .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        var message = LOCALIZATION_UTIL.getMessageByKey(ex.getMessage());
        return new ErrorResponse(message);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponse handle(AlreadyExistsException ex) {
        log.error("AlreadyExistsException: ", ex);
        var message = LOCALIZATION_UTIL.getMessageByKey(ex.getMessage());
        return new ErrorResponse(message);
    }
}