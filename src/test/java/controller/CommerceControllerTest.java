package controller;


import com.inditex.PriceApplication;
import com.inditex.api.CommerceController;
import com.inditex.core.Messages;
import com.inditex.domain.Product;
import com.inditex.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
@AutoConfigureMockMvc
public class CommerceControllerTest {

    private final int PRODUCT_ID = 35455;
    private final int BRAND_ID = 1;

    @Mock
    private Messages messages;
    @Mock
    private PriceService priceService;

    @InjectMocks
    private CommerceController commerceController;

    @Autowired
    private MockMvc mockMvc;
    private Product product;
    private MvcResult mvcResult;

    @Before
    public void setUp() {

        Mockito.reset(priceService);

        commerceController = new CommerceController(priceService, messages);

        product = new Product();

    }


    @Test
    public void getProductOK_predefined1() throws Exception {
        when(priceService.findById(anyString(), Mockito.eq(PRODUCT_ID), Mockito.eq(BRAND_ID))).thenReturn(product);
        ResponseEntity<Product> responseEntity = commerceController.getProduct("2020-06-14T10:00:00", PRODUCT_ID, BRAND_ID);
        mvcResult = perform(get("/product?entryDate=2020-06-14T10:00:00&productId=35455&brandId=1"))
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(priceService).findById(anyString(), Mockito.eq(PRODUCT_ID), Mockito.eq(BRAND_ID));
        assertEquals("Response code has to be 200", HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals("Entity is not the same", product, responseEntity.getBody());
    }

    @Test
    public void getProductKO_predefined1() throws Exception {

        mvcResult = perform(get("/product?entryDate=2027-06-10T10:00:00&productId=35455&brandId=1"))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals("Response code has to be 404, not found", HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void getProductBadRequest() throws Exception {

        mvcResult = perform(get("/product?entryDate=2027-----------06-10T10:00:00&productId=35455&brandId=1"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals("Response code has to be 400, not found", HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

    }

    private ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

}
