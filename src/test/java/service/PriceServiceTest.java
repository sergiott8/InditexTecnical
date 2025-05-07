package service;

import com.inditex.PriceApplication;
import com.inditex.core.PriceRepository;
import com.inditex.domain.Product;
import com.inditex.service.PriceService;
import com.inditex.service.impl.PriceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class PriceServiceTest {

    private final int PRODUCT_ID = 35455;
    private final int BRAND_ID = 1;

    @Autowired
    private PriceRepository priceRepository;

    private PriceService priceService;

    @Before
    public void setUp(){

        priceService = new PriceServiceImpl(priceRepository);
    }


    @Test
    public void test1Predefinided() {
        Product product = priceService.findById("2020-06-14T10:00:00", PRODUCT_ID, BRAND_ID);
        assertEquals("Incorrect product", Double.valueOf(35.50), product.getPrice());
        assertNotNull(product);
    }

    @Test
    public void test2Predefinided() {
        Product product = priceService.findById("2020-06-14T16:00:00", PRODUCT_ID, BRAND_ID);
        assertEquals("Incorrect product", Double.valueOf(25.45), product.getPrice());
        assertNotNull(product);
    }

    @Test
    public void test3Predefinided() {
        Product product = priceService.findById("2020-06-14T21:00:00", PRODUCT_ID, BRAND_ID);

        assertNotNull(product);
        assertEquals("Incorrect product", Double.valueOf(35.50), product.getPrice());

    }

    @Test
    public void test4Predefinided() {
        Product product = priceService.findById("2020-06-15T10:00:00", PRODUCT_ID, BRAND_ID);

        assertNotNull(product);
        assertEquals("Incorrect product", Double.valueOf(30.50), product.getPrice());

    }

    @Test
    public void test5Predefinided() {
        Product product = priceService.findById("2020-06-16T21:00:00", PRODUCT_ID, BRAND_ID);

        assertNotNull(product);
        assertEquals("Incorrect product", Double.valueOf(38.95), product.getPrice());

    }


    @Test(expected = DateTimeParseException.class)
    public void badFormatOfDate() {
        Product product = priceService.findById("2020---06-16T21:00:00", PRODUCT_ID, BRAND_ID);

        assertNotNull(product);

    }

}
