package com.inditex.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class Product {


    private Integer productId;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private Double price;

    private String curr;

    private Integer brandId;

    private Integer priceList;




}
