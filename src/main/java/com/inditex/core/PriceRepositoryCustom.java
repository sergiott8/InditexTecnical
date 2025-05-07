package com.inditex.core;

import com.inditex.domain.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PriceRepositoryCustom {

    //We can use criteriaBuilders too
    @Query(value = "SELECT p FROM Price p WHERE p.productId = ?1 AND p.brandId = ?2 AND ?3 BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Price> findRangeOfProducts(@Param("productId")Integer productId, @Param("brandId")Integer brandId, @Param("entryDate") OffsetDateTime entryDate);


}
