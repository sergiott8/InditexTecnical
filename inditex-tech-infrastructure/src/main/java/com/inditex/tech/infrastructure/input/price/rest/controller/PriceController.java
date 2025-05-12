package com.inditex.tech.infrastructure.input.price.rest.controller;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.domain.price.usecase.PriceSearchUseCase;
import com.inditex.tech.infrastructure.input.price.rest.mapper.PriceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.PriceApi;
import org.openapitools.model.PriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PriceController implements PriceApi {

  private final PriceSearchUseCase priceSearchUseCase;

  private final PriceMapper priceMapper;


  @Override
  public ResponseEntity<PriceDTO> searchPriceProduct(final OffsetDateTime entryDate, final Integer productId, final Integer brandId)
  {
    if (this.validateRequest(entryDate, productId, brandId)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.info("REST: searchPriceProduct - DateTime: {}, ProductId: {} and brandId: {} ", entryDate, productId, brandId);

    final PriceProduct priceProduct = this.priceSearchUseCase.searchPrice(entryDate, productId, brandId);
    final PriceDTO priceDTO = this.priceMapper.mapPriceProductToPriceDTO(priceProduct);

    log.info("REST: searchPriceProductResult - ProductId: {} and price: {} ", priceDTO.getProductId(), priceDTO.getPrice());

    return ResponseEntity.status(HttpStatus.OK).body(priceDTO);
  }

  private boolean validateRequest(final OffsetDateTime entryDate, final Integer productId, final Integer brandId) {
    return Objects.isNull(entryDate) || Objects.isNull(productId) || Objects.isNull(brandId);
  }
}
