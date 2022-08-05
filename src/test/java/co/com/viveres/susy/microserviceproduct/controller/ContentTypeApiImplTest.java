package co.com.viveres.susy.microserviceproduct.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static co.com.viveres.susy.microserviceproduct.DummyMock.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.viveres.susy.microserviceproduct.service.IContentService;

//@WebMvcTest(ContentTypeApiImpl.class)
class ContentTypeApiImplTest {
	
	/*@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IContentService service;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Test
	void findAllContentTest() throws Exception {
		
		when(this.service.findAllContent()).thenReturn(contentOutputDtoList());
		
		mvc.perform(get("/v1/content-type").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
		.andExpect(content().json(this.objectMapper.writeValueAsString(contentOutputDtoList())));
		
	}*/

}
