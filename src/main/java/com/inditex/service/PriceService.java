package com.inditex.service;


import com.inditex.domain.Product;


public interface PriceService {

    /**
     * Search a Product by ID, date and brandId
     *
     * @param entryDate Date of searching product
     * @param productId Identifier from the product
     * @param brandId  Identifier from the brand
     */
    Product findById(String entryDate, Integer productId, Integer brandId);




}



