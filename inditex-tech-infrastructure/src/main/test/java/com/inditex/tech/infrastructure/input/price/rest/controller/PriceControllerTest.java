package com.inditex.tech.infrastructure.input.price.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

import com.inditex.tech.infrastructure.input.price.rest.util.DateUtils;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.domain.price.exception.PriceProductBusinessException;
import com.inditex.tech.domain.price.usecase.PriceSearchUseCase;
import com.inditex.tech.infrastructure.input.price.rest.mapper.PriceMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.PriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

  private static final String CURRENCY_EUR = "EUR";

  @Mock
  private PriceSearchUseCase priceSearchUseCase;

  @Mock
  private PriceMapper priceMapper;

  @InjectMocks
  private PriceController priceController;

  @Test
  void should_return_price_when_all_parameters_are_valid() {
    // Given
    final Integer validProductId = 35455;
    final Integer validBrandId = 1;

    final PriceProduct priceProduct =
        PriceProduct.builder().productId(validProductId).brandId(validBrandId).startDate(DateUtils.parseCustomDateTime("2020-06-14-00.00.00"))
            .endDate(DateUtils.parseCustomDateTime("2020-12-31-23.59.59")).priceList(1).price(35.50).priority(0).curr(CURRENCY_EUR).build();

    final OffsetDateTime validDate = priceProduct.getStartDate();


    final PriceDTO priceDTO = new PriceDTO();
    priceDTO.setProductId(validProductId);
    priceDTO.setBrandId(validBrandId);
    priceDTO.setStartDate(priceProduct.getStartDate());
    priceDTO.setEndDate(priceProduct.getEndDate());
    priceDTO.setPriceList(priceProduct.getPriceList());
    priceDTO.setPrice(priceProduct.getPrice());

    // When
    when(this.priceSearchUseCase.searchPrice(validDate, validProductId, validBrandId)).thenReturn(priceProduct);
    when(this.priceMapper.mapPriceProductToPriceDTO(priceProduct)).thenReturn(priceDTO);

    final ResponseEntity<PriceDTO> response = this.priceController.searchPriceProduct(validDate, validProductId, validBrandId);

    // Then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(priceDTO, response.getBody());
    verify(this.priceSearchUseCase, times(1)).searchPrice(validDate, validProductId, validBrandId);
    verify(this.priceMapper, times(1)).mapPriceProductToPriceDTO(priceProduct);
  }

  @Test
  void should_return_bad_request_when_entry_date_is_null() {
    // Given
    final Integer productId = 35455;
    final Integer brandId = 1;

    // When
    final ResponseEntity<PriceDTO> response = this.priceController.searchPriceProduct(null, productId, brandId);

    // Then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(this.priceSearchUseCase, never()).searchPrice(any(), any(), any());
    verify(this.priceMapper, never()).mapPriceProductToPriceDTO(any());
  }

  @Test
  void should_return_bad_request_when_product_id_is_null() {
    // Given
    final OffsetDateTime entryDate = OffsetDateTime.now();
    final Integer brandId = 1;

    // When
    final ResponseEntity<PriceDTO> response = priceController.searchPriceProduct(entryDate, null, brandId);

    // Then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(this.priceSearchUseCase, never()).searchPrice(any(), any(), any());
    verify(this.priceMapper, never()).mapPriceProductToPriceDTO(any());
  }

  @Test
  void should_return_bad_request_when_brand_id_is_null() {
    // Given
    final OffsetDateTime entryDate = OffsetDateTime.now();
    final Integer productId = 35455;

    // When
    final ResponseEntity<PriceDTO> response = this.priceController.searchPriceProduct(entryDate, productId, null);

    // Then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(this.priceSearchUseCase, never()).searchPrice(any(), any(), any());
    verify(this.priceMapper, never()).mapPriceProductToPriceDTO(any());
  }

  @Test
  void should_throw_exception_when_price_not_found() {
    // Given
    final OffsetDateTime entryDate = OffsetDateTime.now();
    final Integer productId = 35456;
    final Integer brandId = 1;

    // When
    when(this.priceSearchUseCase.searchPrice(entryDate, productId, brandId))
        .thenThrow(new PriceProductBusinessException("Price not found for productId: " + productId + " and brandId " + brandId));

    // Then
    PriceProductBusinessException exception = assertThrows(
        PriceProductBusinessException.class,
        () -> this.priceController.searchPriceProduct(entryDate, productId, brandId)
    );

    assertEquals("Price not found for productId: " + productId + " and brandId " + brandId, exception.getMessage());
    verify(this.priceSearchUseCase, times(1)).searchPrice(entryDate, productId, brandId);
    verify(this.priceMapper, never()).mapPriceProductToPriceDTO(any());
  }

}
