package com.edusalguero.rexoubador.infraestructure.spring.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ExceptionHandlerController {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity handleNoHandlerFoundException() {
        return errorResponse("40004", "Not found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException error) {
        return errorResponse("40005", "Not allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ResponseEntity<ApiErrorResponse> errorResponse(String code, String message, HttpStatus status) {
        logger.error("Exception! " + message);
        return new ResponseEntity<ApiErrorResponse>(new ApiErrorResponse(code, message), status);
    }

}
