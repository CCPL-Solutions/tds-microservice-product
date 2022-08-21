package co.com.viveres.susy.microserviceproduct.controller;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microserviceproduct.config.controller.ProductApiImpl;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.repository.IMeasureTypeRepository;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductApiImpl.class)
class ProductApiImplTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IProductService service;

    @MockBean
    private IBrandRepository brandRepository;

    @MockBean
    private IContentRepository contentRepository;

    @MockBean
    private IMeasureTypeRepository measureTypeRepository;

    @MockBean
    private IProductRepository productRepository;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createTest() throws JsonProcessingException, Exception {
        when(this.service.create(any(ProductDto.class))).thenReturn(productOutputDto());

        this.mvc.perform(post("/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(productInputDto())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(productOutputDto())));
    }

    @Test
    void findAllTest() throws JsonProcessingException, Exception {

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
    void findByIdTest() throws JsonProcessingException, Exception {
        when(this.service.findById(anyLong())).thenReturn(productOutputDto());

        this.mvc.perform(get("/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(productOutputDto())));
    }

    @Test
    void updateTest() throws JsonProcessingException, Exception {
        this.mvc.perform(put("/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productInputDtoUpdate())))
                .andExpect(status().isOk());
    }

    @Test
    void stockManagementByProductTest() throws JsonProcessingException, Exception {
        this.mvc.perform(put("/v1/products/1/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(stockDto("add"))))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}
