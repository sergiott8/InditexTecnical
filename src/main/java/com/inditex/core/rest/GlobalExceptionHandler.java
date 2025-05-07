package com.inditex.core.rest;

import com.inditex.core.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

/**
 *
 * Class for handler error/exception controllers
 *
 * @author Sergio Torres 2022.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * Handler for not found product (example: Not found)
     *
     * @param e exception
     * @return response HTTP 404
     */
    @SuppressWarnings("unused")
    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Void> exceptionHandlerNotFound(final Throwable e) {
        log.info("Exception in REST service, product not found", e);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Handler for bad request
     *
     * @param e exception
     * @return response HTTP 400
     */
    @SuppressWarnings("unused")
    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<Void> exceptionHandlerBadDateFormat(final Throwable e) {
        log.info("Exception in REST service, bad request", e);
        
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
