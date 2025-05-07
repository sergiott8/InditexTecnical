package com.inditex.core;

import com.inditex.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer>, PriceRepositoryCustom {



}
