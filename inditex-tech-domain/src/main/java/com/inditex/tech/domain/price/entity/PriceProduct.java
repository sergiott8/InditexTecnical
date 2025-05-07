package com.inditex.tech.domain.price.entity;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PriceProduct {

  private final Integer productId;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  private Double price;

  private String curr;

  private final Integer brandId;

  private Integer priceList;

}
