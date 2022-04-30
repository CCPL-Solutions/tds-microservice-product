package co.com.viveres.susy.microserviceproduct.controller;

import static co.com.viveres.susy.microserviceproduct.DummyMock.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microserviceproduct.dto.ProductInputDto;
import co.com.viveres.susy.microserviceproduct.dto.ProductOutputDto;
import co.com.viveres.susy.microserviceproduct.service.IProductService;

//@WebMvcTest(ProductApiImpl.class)
class ProductApiImplTest {
	
	/*@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IProductService service;
	
	@MockBean
	private IMessageRepository messageRepository;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Ignore
	@Test
	void createTest() throws JsonProcessingException, Exception {
		when(this.service.create(any(ProductInputDto.class))).thenReturn(productOutputDto());
		
		this.mvc.perform(post("/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(productInputDto())))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(this.objectMapper.writeValueAsString(productOutputDto())));
	}
	
	@Ignore
	@Test
	void findAllTest() throws JsonProcessingException, Exception {
		
		List<ProductOutputDto> productOutputDtoList = productOutputDtoList();
		
		when(this.service.findAll()).thenReturn(productOutputDtoList);
		
		this.mvc.perform(get("/v1/product")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", Matchers.hasSize(3)))
			.andExpect(content().json(this.objectMapper.writeValueAsString(productOutputDtoList)));
	}
	
	@Ignore
	@Test
	void findByIdTest() throws JsonProcessingException, Exception {
		when(this.service.findById(anyLong())).thenReturn(productOutputDto());
		
		this.mvc.perform(get("/v1/product/1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(this.objectMapper.writeValueAsString(productOutputDto())));
	}
	
	@Ignore
	@Test	
	void updateTest() throws JsonProcessingException, Exception {
		this.mvc.perform(put("/v1/product/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(productInputDtoUpdate())))
			.andExpect(status().isOk());
	}
	
	@Ignore
	@Test	
	void stockManagementByProductTest() throws JsonProcessingException, Exception {
		this.mvc.perform(put("/v1/product/1/stock")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(stockDto("add"))))
			.andExpect(status().isOk());
	}*/

}
