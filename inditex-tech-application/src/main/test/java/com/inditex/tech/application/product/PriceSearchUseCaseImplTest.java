package com.inditex.tech.application.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.domain.price.exception.PriceProductBusinessException;
import com.inditex.tech.domain.price.repository.PriceRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceSearchUseCaseImplTest {

  private static final String CURRENCY_EUR = "EUR";

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceSearchUseCaseImpl priceSearchUseCase;

  @Test
  void should_return_price_when_found() {
    // Given
    final Integer productId = 35455;
    final Integer brandId = 1;
    final OffsetDateTime entryDate = OffsetDateTime.now();

    final PriceProduct expectedPriceProduct = PriceProduct.builder()
        .productId(productId)
        .brandId(brandId)
        .startDate(OffsetDateTime.now().minusDays(1))
        .endDate(OffsetDateTime.now().plusDays(1))
        .priceList(1)
        .price(35.50)
        .priority(0)
        .curr(CURRENCY_EUR)
        .build();

    // When
    when(this.priceRepository.findApplicablePrice(productId, brandId, entryDate))
        .thenReturn(Optional.of(expectedPriceProduct));

    final PriceProduct result = this.priceSearchUseCase.searchPrice(entryDate, productId, brandId);

    // Then
    assertEquals(expectedPriceProduct, result);
  }

  @Test
  void should_throw_exception_when_price_not_found() {
    // Given
    final Integer productId = 35455;
    final Integer brandId = 1;
    final OffsetDateTime entryDate = OffsetDateTime.now();

    // When
    when(this.priceRepository.findApplicablePrice(productId, brandId, entryDate))
        .thenReturn(Optional.empty());

    // Then
    final PriceProductBusinessException exception = assertThrows(PriceProductBusinessException.class,
        () -> this.priceSearchUseCase.searchPrice(entryDate, productId, brandId));

    assertEquals("Price not found for productId: " + productId + " and brandId " + brandId, exception.getMessage());
  }
}