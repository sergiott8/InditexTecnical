package com.inditex.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;


import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class Price {

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
