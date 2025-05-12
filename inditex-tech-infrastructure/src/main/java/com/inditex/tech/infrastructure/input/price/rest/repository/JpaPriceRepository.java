package com.inditex.tech.infrastructure.input.price.rest.repository;

import java.time.OffsetDateTime;

import com.inditex.tech.infrastructure.input.price.rest.entity.PriceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Integer> {


  @Query("SELECT p FROM price p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN p.startDate AND p" 
      + ".endDate ORDER BY p.priority DESC")
  PriceEntity findApplicablePrice(
      @Param("productId") Integer productId,
      @Param("brandId") Integer brandId,
      @Param("applicationDate") OffsetDateTime applicationDate
  );



}
