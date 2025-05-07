package com.inditex.api;

import com.inditex.core.Messages;
import com.inditex.domain.Product;
import com.inditex.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Inditex Controller
 *
 * @author Sergio Torres 2025.
 */

@RestController
public class CommerceController {


    private static final Logger LOG = LoggerFactory.getLogger(CommerceController.class);
    private static final String NOT_FOUND_ID = "messages.product.notfound.id";
    private final PriceService priceService;
    private final Messages messages;

    @Autowired
    public CommerceController(PriceService priceService, Messages messages) {
        this.priceService = priceService;
        this.messages = messages;

    }

    @RequestMapping("/")
    public void log() {
        LOG.info("Logging from /");
    }

    /** Get a price-product using a identifier, a specifically brand and a date
     *
     * @param entryDate Date of searching product
     * @param productId Identifier from the product
     * @param brandId  Identifier from the brand
     * @return product in that entryDate
     */
   @GetMapping(value = "/product")
   public ResponseEntity<Product> getProduct(@RequestParam(name = "entryDate") String entryDate,
                                           @RequestParam(name = "productId") Integer productId,
                                           @RequestParam(name = "brandId") Integer brandId) {
        final Product product = priceService.findById(entryDate, productId, brandId);
        if (product == null) {
            LOG.info(messages
                    .get(NOT_FOUND_ID),
                    productId);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
