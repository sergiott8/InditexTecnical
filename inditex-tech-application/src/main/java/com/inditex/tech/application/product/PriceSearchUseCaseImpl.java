package com.inditex.tech.application.product;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.domain.price.exception.PriceProductBusinessException;
import com.inditex.tech.domain.price.repository.PriceRepository;
import com.inditex.tech.domain.price.usecase.PriceSearchUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PriceSearchUseCaseImpl implements PriceSearchUseCase {

  private final PriceRepository priceRepository;

  @Override
  public PriceProduct searchPrice(final OffsetDateTime entryDate, final Integer productId, final Integer brandId) {

    final Optional<PriceProduct> priceProduct = this.priceRepository.findApplicablePrice(productId, brandId, entryDate);

    return priceProduct.orElseThrow(
        () -> new PriceProductBusinessException("Price not found for productId: " + productId + " and brandId " + brandId));

  }
}
