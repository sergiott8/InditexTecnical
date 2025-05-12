package com.inditex.tech.boot.price;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = com.inditex.tech.boot.price.TestConfig.class)
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_price_for_product_35455_brand_1_at_10_00_on_day_14() throws Exception {
        // Test 1: Request at 10:00 on day 14 for product 35455 and brand 1 (ZARA)
        // Expected: Price 35.50 EUR (record 1)

        OffsetDateTime requestDate = OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.UTC);

        mockMvc.perform(get("/price")
                .param("entryDate", requestDate.toString())
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void should_return_price_for_product_35455_brand_1_at_16_00_on_day_14() throws Exception {
        // Test 2: Request at 16:00 on day 14 for product 35455 and brand 1 (ZARA)
        // Expected: Price 25.45 EUR (record 2 has higher priority)

        OffsetDateTime requestDate = OffsetDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneOffset.UTC);

        mockMvc.perform(get("/price")
                .param("entryDate", requestDate.toString())
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void should_return_price_for_product_35455_brand_1_at_21_00_on_day_14() throws Exception {
        // Test 3: Request at 21:00 on day 14 for product 35455 and brand 1 (ZARA)
        // Expected: Price 35.50 EUR (record 1)

        OffsetDateTime requestDate = OffsetDateTime.of(2020, 6, 14, 21, 0, 0, 0, ZoneOffset.UTC);

        mockMvc.perform(get("/price")
                .param("entryDate", requestDate.toString())
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void should_return_price_for_product_35455_brand_1_at_10_00_on_day_15() throws Exception {
        // Test 4: Request at 10:00 on day 15 for product 35455 and brand 1 (ZARA)
        // Expected: Price 30.50 EUR (record 3 has higher priority)

        OffsetDateTime requestDate = OffsetDateTime.of(2020, 6, 15, 10, 0, 0, 0, ZoneOffset.UTC);

        mockMvc.perform(get("/price")
                .param("entryDate", requestDate.toString())
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void should_return_price_for_product_35455_brand_1_at_21_00_on_day_16() throws Exception {
        // Test 5: Request at 21:00 on day 16 for product 35455 and brand 1 (ZARA)
        // Expected: Price 38.95 EUR (record 4 has higher priority)

        OffsetDateTime requestDate = OffsetDateTime.of(2020, 6, 16, 21, 0, 0, 0, ZoneOffset.UTC);

        mockMvc.perform(get("/price")
                .param("entryDate", requestDate.toString())
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
