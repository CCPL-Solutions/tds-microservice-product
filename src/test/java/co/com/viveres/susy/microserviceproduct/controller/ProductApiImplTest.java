package co.com.viveres.susy.microserviceproduct.controller;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microserviceproduct.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static co.com.viveres.susy.microserviceproduct.DummyMock.getProductDtoPage;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productInputDto;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productInputDtoUpdate;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productOutputDto;
import static co.com.viveres.susy.microserviceproduct.DummyMock.stockDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = ProductApiImpl.class)
class ProductApiImplTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IProductService service;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createTest() throws Exception {
        when(this.service.create(any(ProductDto.class))).thenReturn(productOutputDto());

        this.mvc.perform(post("/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(productInputDto())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(productOutputDto())));
    }

    @Test
    void findAllTest() throws Exception {

        Page<ProductDto> productDtoPage = getProductDtoPage();

        when(this.service.findAll(anyInt(), anyInt(), anyString(), anyString(), anyString())).thenReturn(productDtoPage);

        this.mvc.perform(get("/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr("page", 0)
                        .requestAttr("size", 10)
                        .requestAttr("sort", "name")
                        .requestAttr("productName", "Arroz"))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdTest() throws Exception {
        when(this.service.findById(anyLong())).thenReturn(productOutputDto());

        this.mvc.perform(get("/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(productOutputDto())));
    }

    @Test
    void updateTest() throws Exception {
        this.mvc.perform(put("/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productInputDtoUpdate())))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTest() throws Exception {
        this.mvc.perform(delete("/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productInputDtoUpdate())))
                .andExpect(status().isOk());
    }

    @Test
    void stockManagementByProductTest() throws Exception {
        this.mvc.perform(put("/v1/products/1/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(stockDto("add"))))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}
