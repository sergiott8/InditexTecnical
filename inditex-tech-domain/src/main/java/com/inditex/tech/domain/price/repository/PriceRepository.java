package com.inditex.tech.domain.price.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.inditex.tech.domain.price.entity.PriceProduct;

public interface PriceRepository {

  Optional<PriceProduct> findApplicablePrice(Integer productId, Integer brandId, OffsetDateTime date);


}
