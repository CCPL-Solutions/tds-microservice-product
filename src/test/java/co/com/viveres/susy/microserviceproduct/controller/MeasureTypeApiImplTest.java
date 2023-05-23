package co.com.viveres.susy.microserviceproduct.controller;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microserviceproduct.DummyMock;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static co.com.viveres.susy.microserviceproduct.DummyMock.productOutputDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MeasureTypeApiImpl.class)
class MeasureTypeApiImplTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMeasureTypeService service;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createTest() throws Exception {
        Mockito.when(service.create(ArgumentMatchers.any(MeasureTypeDto.class))).thenReturn(DummyMock.measureTypeDtoIn());
        this.mvc.perform(MockMvcRequestBuilders.post("/v1/measure-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(DummyMock.measureTypeDtoIn())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(DummyMock.measureTypeDtoIn())));
    }

    @Test
    void findAllTest() throws Exception {
        Mockito.when(service.findAll()).thenReturn(DummyMock.measureDtoListOut());
        this.mvc.perform(get("/v1/measure-type"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(asJsonString(DummyMock.measureDtoListOut())));
    }

    @Test
    void findByIdTest() {
    }

    @Test
    void updateTest() {
    }

    private String asJsonString(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}