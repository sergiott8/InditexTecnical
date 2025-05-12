package com.inditex.tech.infrastructure.input.price.rest.exception;

import com.inditex.tech.domain.price.exception.PriceProductBusinessException;
import com.inditex.tech.infrastructure.input.price.rest.mapper.PriceExceptionMapper;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.inditex.tech.infrastructure.input.rest.price")
@RequiredArgsConstructor
public class PriceExceptionHandlerAdvice {

  private final PriceExceptionMapper priceExceptionMapper;

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDTO> handleException(final IllegalArgumentException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(this.priceExceptionMapper.mapExceptionToErrorResponseDTO(exception));
  }

  @ExceptionHandler(PriceProductBusinessException.class)
  public ResponseEntity<ErrorResponseDTO> handleExceptionBusiness(final PriceProductBusinessException exception) {
    final ErrorResponseDTO errorResponseDTO = this.priceExceptionMapper.mapExceptionToErrorResponseDTO(exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
  }



}
