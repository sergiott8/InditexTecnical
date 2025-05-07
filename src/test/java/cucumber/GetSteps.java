package cucumber;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.inditex.PriceApplication;
import com.inditex.api.CommerceController;
import com.inditex.core.Messages;
import com.inditex.core.PriceRepository;
import com.inditex.domain.Product;
import com.inditex.service.PriceService;
import com.inditex.service.impl.PriceServiceImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@Import({PriceServiceImpl.class, PriceRepository.class})
@SpringBootTest(classes = PriceApplication.class)
@AutoConfigureMockMvc
public class GetSteps {

    private final Integer PRODUCT_ID = 33455;

    private final Integer BRAND_ID = 1;

    private static final String NOT_FOUND_ID = "messages.product.notfound.id";

    @Autowired
    private MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private PriceService priceService;

    @Autowired
    private Messages messages;

    private MvcResult result;

    private Product product;

    @Before
    public void init() {

        priceService = Mockito.mock(PriceService.class);

        messages = Mockito.mock(Messages.class);

        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);

        final CommerceController commerceController = new CommerceController(priceService, messages);
        mvc = MockMvcBuilders.standaloneSetup(commerceController).build();

    }


    @Given("^tenemos el producto existente$")
    public void initializeData() {

        product = new Product();
        product.setProductId(PRODUCT_ID);
        product.setBrandId(BRAND_ID);
        product.setPrice(30.0);
        product.setPriceList(1);

        product.setCurr("EUR");

    }


    @When("^pido un producto: (\\d+) y con brand: (\\d+) en una fecha determinada \"(.*)\"$")
    public void requestProductByDate(int productId, int brandId, String date) throws Exception {

        when(priceService.findById(date, productId, brandId)).thenReturn(product);
        when(messages.get(NOT_FOUND_ID)).thenReturn("No se encuentra ");

        result = perform(MockMvcRequestBuilders.get("/product?entryDate=" + date +"&productId=" + productId + "&brandId=" + brandId))
                .andReturn();
    }

    @When("^pido un producto no existente: (\\d+) y con brand: (\\d+) en una fecha determinada \"(.*)\"$")
    public void requestProductNotExist(int productId, int brandId, String date) throws Exception {

        when(priceService.findById(date, productId, brandId)).thenReturn(null);
        when(messages.get(NOT_FOUND_ID)).thenReturn("No se encuentra ");

        result = perform(MockMvcRequestBuilders.get("/product?entryDate=" + date +"&productId="+ productId + "&brandId=" + brandId))
                .andReturn();
    }

    @When("^pido un producto existente: (\\d+) y con brand: (\\d+) en una fecha mal formada \"(.*)\"$")
    public void requestProductByDateBadFormat(int productId, int brandId, String date) throws Exception {

        when(priceService.findById(date, productId, brandId)).thenThrow(DateTimeParseException.class);
        when(messages.get(NOT_FOUND_ID)).thenReturn("No se encuentra ");

        result = perform(MockMvcRequestBuilders.get("/product?entryDate=" + date +"&productId="+ productId + "&brandId=" + brandId))
                .andReturn();
    }



    @Then("^obtengo el producto seleccionado$")
    public void checkResponseAll() throws Exception {

        Product productResult = mapper.readValue(result.getResponse().getContentAsString(), Product.class);
        assertNotNull("Response data was null", productResult);
        assertEquals("Product has to be the same", productResult.getProductId(), product.getProductId());

    }

    @Then("^el codigo de respuesta es (\\d+)$")
    public void checkResponseCode(int code) {

        Assert.assertEquals("Response code has to be the same", code, result.getResponse().getStatus());

    }

    private ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mvc.perform(builder);
    }
}
