package com.inditex.tech.infrastructure.input.price.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.ErrorResponseDTO;

@ExtendWith(MockitoExtension.class)
class PriceExceptionMapperTest {

  private final PriceExceptionMapper priceExceptionMapper = Mappers.getMapper(PriceExceptionMapper.class);

  @Test
  void should_map_exception_to_error_response_dto() {
    // Given
    final String errorMessage = "Test error message";
    final Exception exception = new Exception(errorMessage);

    // When
    final ErrorResponseDTO errorResponseDTO = this.priceExceptionMapper.mapExceptionToErrorResponseDTO(exception);

    // Then
    assertNull(errorResponseDTO.getCode());
    assertEquals(errorMessage, errorResponseDTO.getTitle());
    assertEquals(errorMessage, errorResponseDTO.getDescription());
  }

  @Test
  void should_map_exception_with_null_message_to_error_response_dto() {
    // Given
    final Exception exception = new Exception();

    // When
    final ErrorResponseDTO errorResponseDTO = this.priceExceptionMapper.mapExceptionToErrorResponseDTO(exception);

    // Then
    assertNull(errorResponseDTO.getCode());
    assertNull(errorResponseDTO.getTitle());
    assertNull(errorResponseDTO.getDescription());
  }

  @Test
  void should_map_null_exception_to_error_response_dto() {
    // When
    final ErrorResponseDTO errorResponseDTO = this.priceExceptionMapper.mapExceptionToErrorResponseDTO(null);

    // Then
    assertNull(errorResponseDTO);
  }

  @Test
  void should_map_runtime_exception_to_error_response_dto() {
    // Given
    final String errorMessage = "Runtime exception message";
    final RuntimeException exception = new RuntimeException(errorMessage);

    // When
    final ErrorResponseDTO errorResponseDTO = this.priceExceptionMapper.mapExceptionToErrorResponseDTO(exception);

    // Then
    assertNull(errorResponseDTO.getCode());
    assertEquals(errorMessage, errorResponseDTO.getTitle());
    assertEquals(errorMessage, errorResponseDTO.getDescription());
  }

  @Test
  void should_map_illegal_argument_exception_to_error_response_dto() {
    // Given
    final String errorMessage = "Illegal argument exception message";
    final IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

    // When
    final ErrorResponseDTO errorResponseDTO = this.priceExceptionMapper.mapExceptionToErrorResponseDTO(exception);

    // Then
    assertNull(errorResponseDTO.getCode());
    assertEquals(errorMessage, errorResponseDTO.getTitle());
    assertEquals(errorMessage, errorResponseDTO.getDescription());
  }
}
