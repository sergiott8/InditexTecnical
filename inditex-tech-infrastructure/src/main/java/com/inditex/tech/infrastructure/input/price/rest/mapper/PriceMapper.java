package com.inditex.tech.infrastructure.input.price.rest.mapper;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.infrastructure.input.price.rest.entity.PriceEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.PriceDTO;

@Mapper
public interface PriceMapper {
  
  @Mapping(source = "curr", target = "currency")
  PriceDTO mapPriceProductToPriceDTO(final PriceProduct price);
  
  PriceProduct mapPriceEntityToPriceProduct(final PriceEntity priceEntity);

}
