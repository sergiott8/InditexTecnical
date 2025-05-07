package com.inditex.tech.infrstructure.input.price.rest.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.inditex.tech.domain.price.entity.PriceProduct;
import com.inditex.tech.domain.price.repository.PriceRepository;
import com.inditex.tech.infrstructure.input.price.rest.entity.PriceEntity;
import com.inditex.tech.infrstructure.input.price.rest.mapper.PriceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

  private final JpaPriceRepository priceRepository;

  private final PriceMapper priceMapper;

  @Override
  public Optional<PriceProduct> findApplicablePrice(Integer productId, Integer brandId, OffsetDateTime date) {

    final PriceEntity priceEntity =
        this.priceRepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId,
                                                                                                                                brandId,
                                                                                                                                date);

    final PriceProduct priceProduct = this.priceMapper.mapPriceEntityToPriceProduct(priceEntity);
    return Optional.of(priceProduct);
  }
}
