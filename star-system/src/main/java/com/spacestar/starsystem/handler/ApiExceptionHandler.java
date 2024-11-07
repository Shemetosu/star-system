package com.spacestar.starsystem.handler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * example:
     * <pre>
     * {
     *   "status": 404,
     *   "error": "NOT_FOUND",
     *   "message": "no handler found"
     * }
     * </pre>
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
                                                                   HttpHeaders httpHeaders,
                                                                   HttpStatusCode statusCode,
                                                                   WebRequest webRequest) {
        var apiError = new ApiError(statusCode, "No handler found", exception);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 400,
     *   "error": "BAD_REQUEST",
     *   "message": "Validation exception",
     *   "errors": [
     *     {
     *       "object": "userRegistrationDto",
     *       "field": "userId",
     *       "value": null,
     *       "message": "User doesn't exist"
     *     },
     *     {
     *       "object": "userRegistrationDto",
     *       "field": "userId",
     *       "value": null,
     *       "message": "The field cannot be null"
     *     }
     *   ]
     * }
     * </pre>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders httpHeaders,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest webRequest) {
        var apiError = new ApiError(statusCode, "Validation exception", exception);
        apiError.addValidationFieldError(exception.getBindingResult().getFieldErrors());
        apiError.addValidationObjectError(exception.getBindingResult().getAllErrors());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 400,
     *   "error": "BAD_REQUEST",
     *   "message": "Malformed JSON request"
     * }
     * </pre>
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders httpHeaders,
                                                                  HttpStatusCode statusCode,
                                                                  WebRequest webRequest) {
        var apiError = new ApiError(statusCode, "Malformed JSON request", exception);
        return new ResponseEntity<>(apiError, Objects.requireNonNull(HttpStatus.resolve(statusCode.value())));
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 400,
     *   "error": "BAD_REQUEST",
     *   "message": "The parameter 'field' of value 'xxx' could not be converted to type 'Integer'"
     * }
     * </pre>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiError> handle(MethodArgumentTypeMismatchException exception,
                                              WebRequest webRequest) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(
                String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                        exception.getName(),
                        exception.getValue(),
                        Objects.requireNonNull(exception.getRequiredType()).getSimpleName())
        );
        // apiError.setDebugMessage(methodArgumentTypeMismatchException.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 404,
     *   "error": "NOT_FOUND",
     *   "message": "Entity not found exception"
     * }
     * </pre>
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiError> handle(EntityNotFoundException exception,
                                              WebRequest webRequest) {
        var apiError = new ApiError(HttpStatus.NOT_FOUND, "Entity not found exception", exception);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 302,
     *   "error": "FOUND",
     *   "message": "Entity exists exception"
     * }
     * </pre>
     */
    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<ApiError> handle(EntityExistsException exception,
                                              WebRequest webRequest) {
        var apiError = new ApiError(HttpStatus.FOUND, "Entity exists exception", exception);
        return new ResponseEntity<>(apiError, HttpStatus.FOUND);
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 404,
     *   "error": "NOT_FOUND",
     *   "message": "Empty result (from database)"
     * }
     * </pre>
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handle(EmptyResultDataAccessException exception,
                                              WebRequest webRequest) {
        var apiError = new ApiError(HttpStatus.NOT_FOUND, "Empty result (from database)", exception);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * example:
     * <pre>
     * {
     *   "status": 400,
     *   "error": "BAD_REQUEST",
     *   "message": "The parameter 'field' of value 'xxx' could not be converted to type 'Integer'"
     * }
     * </pre>
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ApiError> handle(ValidationException exception,
                                              WebRequest webRequest) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Error: " + exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

//    /**
//     * example:
//     * <pre>
//     * {
//     *   "status": 400,
//     *   "error": "BAD_REQUEST",
//     *   "message": ""
//     * }
//     * </pre>
//     */
//    @ExceptionHandler(BadParametersException.class)
//    protected ResponseEntity<ApiError> handle(BadParametersException exception,
//                                              WebRequest webRequest) {
//        var apiError = new ApiError(HttpStatus.BAD_REQUEST);
//        apiError.setMessage("Error: " + exception.getMessage());
//        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//    }
}