package com.inditex.service.impl;


import com.inditex.core.PriceRepository;
import com.inditex.domain.Price;
import com.inditex.domain.Product;
import com.inditex.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }


    @Override
    public Product findById(String entryDate, Integer productId, Integer brandId){


        OffsetDateTime fromDate = convertEntryDate(entryDate);

        List<Price> priceList =  priceRepository.findRangeOfProducts(productId, brandId, fromDate);

        if(priceList.isEmpty()){

            return null;
        }

        Price pricePriority = priceList.get(0);


        return convertProduct(pricePriority);
    }

    private OffsetDateTime convertEntryDate(String entryDate) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .parseDefaulting(ChronoField.OFFSET_SECONDS, 0)
                .toFormatter();

        return OffsetDateTime.parse(entryDate, formatter);
    }

    private Product convertProduct(Price pricePriority) {
       return Product.builder().productId(pricePriority.getProductId())
                .brandId(pricePriority.getBrandId())
                .price(pricePriority.getPrice())
                .priceList(pricePriority.getPriceList())
                .startDate(pricePriority.getStartDate())
                .endDate(pricePriority.getEndDate())
                .curr(pricePriority.getCurr())
                .build();

    }
}
