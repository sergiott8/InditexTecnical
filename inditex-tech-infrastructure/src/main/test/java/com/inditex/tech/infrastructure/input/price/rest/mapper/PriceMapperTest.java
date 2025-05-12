package com.inditex.tech.infrastructure.input.price.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.OffsetDateTime;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.infrastructure.input.price.rest.entity.PriceEntity;
import com.inditex.tech.infrastructure.input.price.rest.util.DateUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceDTO;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

  private static final String CURRENCY_EUR = "EUR";
  
  private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

  @Test
  void should_map_price_product_to_price_dto() {
    // Given
    final Integer productId = 35455;
    final Integer brandId = 1;
    final OffsetDateTime startDate = DateUtils.parseCustomDateTime("2020-06-14-00.00.00");
    final OffsetDateTime endDate = DateUtils.parseCustomDateTime("2020-12-31-23.59.59");
    final Integer priceList = 1;
    final Double price = 35.50;
    final Integer priority = 0;
    
    final PriceProduct priceProduct = PriceProduct.builder()
        .productId(productId)
        .brandId(brandId)
        .startDate(startDate)
        .endDate(endDate)
        .priceList(priceList)
        .price(price)
        .priority(priority)
        .curr(CURRENCY_EUR)
        .build();

    // When
    final PriceDTO priceDTO = this.priceMapper.mapPriceProductToPriceDTO(priceProduct);

    // Then
    assertEquals(productId, priceDTO.getProductId());
    assertEquals(brandId, priceDTO.getBrandId());
    assertEquals(startDate, priceDTO.getStartDate());
    assertEquals(endDate, priceDTO.getEndDate());
    assertEquals(priceList, priceDTO.getPriceList());
    assertEquals(price, priceDTO.getPrice());
    assertEquals(CURRENCY_EUR, priceDTO.getCurrency());
    assertEquals(priority, priceDTO.getPriority());
  }

  @Test
  void should_return_null_when_mapping_null_price_product_to_price_dto() {
    // When
    final PriceDTO priceDTO = this.priceMapper.mapPriceProductToPriceDTO(null);

    // Then
    assertNull(priceDTO);
  }

  @Test
  void should_map_price_entity_to_price_product() {
    // Given
    final Integer productId = 35455;
    final Integer brandId = 1;
    final OffsetDateTime startDate = DateUtils.parseCustomDateTime("2020-06-14-00.00.00");
    final OffsetDateTime endDate = DateUtils.parseCustomDateTime("2020-12-31-23.59.59");
    final Integer priceList = 1;
    final Double price = 35.50;
    final Integer priority = 0;
    
    final PriceEntity priceEntity = PriceEntity.builder()
        .productId(productId)
        .brandId(brandId)
        .startDate(startDate)
        .endDate(endDate)
        .priceList(priceList)
        .price(price)
        .priority(priority)
        .curr(CURRENCY_EUR)
        .build();

    // When
    final PriceProduct priceProduct = this.priceMapper.mapPriceEntityToPriceProduct(priceEntity);

    // Then
    assertEquals(productId, priceProduct.getProductId());
    assertEquals(brandId, priceProduct.getBrandId());
    assertEquals(startDate, priceProduct.getStartDate());
    assertEquals(endDate, priceProduct.getEndDate());
    assertEquals(priceList, priceProduct.getPriceList());
    assertEquals(price, priceProduct.getPrice());
    assertEquals(CURRENCY_EUR, priceProduct.getCurr());
    assertEquals(priority, priceProduct.getPriority());
  }

  @Test
  void should_return_null_when_mapping_null_price_entity_to_price_product() {
    // When
    final PriceProduct priceProduct = this.priceMapper.mapPriceEntityToPriceProduct(null);

    // Then
    assertNull(priceProduct);
  }
}