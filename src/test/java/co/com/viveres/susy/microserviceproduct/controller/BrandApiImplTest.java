package co.com.viveres.susy.microserviceproduct.controller;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static co.com.viveres.susy.microserviceproduct.DummyMock.brandDtoIn;
import static co.com.viveres.susy.microserviceproduct.DummyMock.brandDtoList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = BrandApiImpl.class)
public class BrandApiImplTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IBrandService service;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createBrandTest() throws Exception {
        // Given
        BrandDto brandDto = brandDtoIn();
        // When
        when(service.create(any(BrandDto.class))).thenReturn(brandDto);
        // Then
        this.mvc.perform(post("/v1/brands")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(brandDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(brandDto)));
    }

    @Test
    void findAllBrandsTest() throws Exception {
        // Given
        List<BrandDto> brandDtoList = brandDtoList();
        // When
        when(service.findAllBrands()).thenReturn(brandDtoList);
        // Then
        this.mvc.perform(get("/v1/brands"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(brandDtoList)));
    }

    @Test
    void findBrandByIdTest() throws Exception {
        // Given
        Long brandId = 1L;
        BrandDto brandDto = brandDtoIn();
        // When
        when(service.findById(anyLong())).thenReturn(brandDto);
        // Then
        this.mvc.perform(get("/v1/brands/{brand-id}", brandId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(brandDto)));
    }

    @Test
    void updateBrandTest() throws Exception {
        // Given
        Long brandId = 1L;
        BrandDto brandDto = brandDtoIn();
        // When
        when(service.findById(anyLong())).thenReturn(brandDto);
        // Then
        this.mvc.perform(put("/v1/brands/{brand-id}", brandId)
                         .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(brandDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBrandTest() throws Exception {
        // Given
        Long brandId = 1L;
        // When
        // Then
        this.mvc.perform(delete("/v1/brands/{brand-id}", brandId))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }

}
