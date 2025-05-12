package com.inditex.tech.domain.price.usecase;

import java.time.OffsetDateTime;

import com.inditex.tech.domain.price.entity.PriceProduct;

public interface PriceSearchUseCase {

  PriceProduct searchPrice(OffsetDateTime entryDate, Integer productId, Integer brandId);

}
