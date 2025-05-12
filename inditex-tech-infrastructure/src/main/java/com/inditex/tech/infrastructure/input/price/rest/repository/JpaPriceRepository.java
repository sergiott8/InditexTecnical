package com.inditex.tech.infrastructure.input.price.rest.repository;

import java.time.OffsetDateTime;

import com.inditex.tech.infrastructure.input.price.rest.entity.PriceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Integer> {

  PriceEntity findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
      Integer productId,
      Integer brandId,
      OffsetDateTime applicationDate
  );


}
