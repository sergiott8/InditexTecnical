package com.inditex.tech.infrastructure.input.price.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.ErrorResponseDTO;

@Mapper
public interface PriceExceptionMapper {

  @Mapping(target = "title", source = "message")
  @Mapping(target = "description", source = "message")
  ErrorResponseDTO mapExceptionToErrorResponseDTO(final Exception exception);

}
