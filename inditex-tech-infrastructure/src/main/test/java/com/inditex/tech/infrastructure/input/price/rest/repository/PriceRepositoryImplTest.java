package com.inditex.tech.infrastructure.input.price.rest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;

import com.inditex.tech.infrastructure.input.price.rest.util.DateUtils;
import java.util.Optional;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.infrastructure.input.price.rest.entity.PriceEntity;
import com.inditex.tech.infrastructure.input.price.rest.mapper.PriceMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryImplTest {

  private static final String CURRENCY_EUR = "EUR";

  @Mock
  private JpaPriceRepository jpaPriceRepository;

  @Mock
  private PriceMapper priceMapper;

  @InjectMocks
  private PriceRepositoryImpl priceRepository;

  @Test
  void should_return_price_when_found() {
    // Given
    final Integer productId = 35455;
    final Integer brandId = 1;
    final OffsetDateTime date = OffsetDateTime.now();

    final PriceEntity priceEntity = PriceEntity.builder()
        .productId(productId)
        .brandId(brandId)
        .startDate(DateUtils.parseCustomDateTime("2020-06-14-00.00.00"))
        .endDate(DateUtils.parseCustomDateTime("2020-12-31-23.59.59"))
        .priceList(1)
        .price(35.50)
        .priority(0)
        .curr(CURRENCY_EUR)
        .build();

    final PriceProduct expectedPriceProduct = PriceProduct.builder()
        .productId(productId)
        .brandId(brandId)
        .startDate(priceEntity.getStartDate())
        .endDate(priceEntity.getEndDate())
        .priceList(priceEntity.getPriceList())
        .price(priceEntity.getPrice())
        .priority(priceEntity.getPriority())
        .curr(priceEntity.getCurr())
        .build();

    // When
    when(this.jpaPriceRepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        productId, brandId, date)).thenReturn(priceEntity);
    when(this.priceMapper.mapPriceEntityToPriceProduct(priceEntity)).thenReturn(expectedPriceProduct);

    final Optional<PriceProduct> result = this.priceRepository.findApplicablePrice(productId, brandId, date);

    // Then
    assertTrue(result.isPresent());
    assertEquals(expectedPriceProduct, result.get());
  }
  

}
