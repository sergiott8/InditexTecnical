package com.inditex.tech.infrastructure.input.price.rest.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity(name = "price")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class PriceEntity {

  @Id
  private Integer productId;

  private OffsetDateTime startDate;

  private OffsetDateTime endDate;

  private Integer priceList;

  private Double price;

  private String curr;

  private Integer brandId;

  private Integer priority;

}