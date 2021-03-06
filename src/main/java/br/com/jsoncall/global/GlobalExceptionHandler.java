package br.com.jsoncall.global;

import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.jsoncall.exception.ServiceException;
import br.com.jsoncall.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseModel handelException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseModel.fail(10000, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /**
     *  400
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseModel handleHttpMessageNotReadableException(NestedRuntimeException e) {
        logger.warn(e.getMessage(), e);
        return ResponseModel.fail(10001, Objects.requireNonNull(e.getMessage()).split(";")[0]);
    }

    /**
     * RequestBody 400
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn(e.getMessage(), e);
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        Objects.isNull(fieldError) ? "null" : fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseModel.fail(10002, message);
    }

    /**
     * RequestParam PathVariable 400
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseModel handleConstraintViolationException(ConstraintViolationException e) {
        logger.warn(e.getMessage(), e);
        String message = e.getConstraintViolations().stream()
                .map(cv -> Objects.isNull(cv) ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseModel.fail(10003, message);
    }

    /**
     *  404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseModel handelNoHandlerFoundException(NoHandlerFoundException e) {
        logger.warn(e.getMessage(), e);
        return ResponseModel.fail(10004, HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    /**
     *  405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseModel handelHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.warn(e.getMessage(), e);
        return ResponseModel.fail(10005, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    /**
     * Service
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseModel handelServiceException(ServiceException e) {
        logger.warn(e.getMessage(), e);
        return ResponseModel.fail(e.getCode(), e.getReason());
    }

}
