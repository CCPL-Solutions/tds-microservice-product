package co.com.viveres.susy.microserviceproduct.service;

import static co.com.viveres.susy.microserviceproduct.DummyMock.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.viveres.susy.microservicecommons.exceptions.GenericException;
import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microserviceproduct.dto.ProductOutputDto;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private IProductRepository productRepository;
	@Mock
	private IContentRepository contentRepository;
	@Mock
	private IMessageRepository messageRepository;
	@Mock
	private IBrandRepository brandRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void createProductAlreadyExistTest() {
		when(this.contentRepository.findById(anyLong())).thenReturn(contentEntity());					
		assertThrows(GenericException.class, () -> this.productService.create(productInputDto()));		
		verify(this.contentRepository).findById(anyLong());		

	}

	@Test
	void createOk() {
		when(this.contentRepository.findById(anyLong())).thenReturn(contentEntity());
		when(this.brandRepository.findById(anyLong())).thenReturn(brandEntity());
		when(this.productRepository.findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class))).thenReturn(Optional.empty());
		when(this.productRepository.save(any(ProductEntity.class))).thenReturn(productEntity().orElseThrow());

		ProductOutputDto productOutputDtoExpected = productOutputDto();
		ProductOutputDto productOutputDtoActual = this.productService.create(productInputDto());
		
		verify(this.contentRepository, times(2)).findById(anyLong());
		verify(this.productRepository).findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class));
		
		assertNotNull(productOutputDtoActual);
		assertEquals(productOutputDtoExpected, productOutputDtoActual);			
	}
	
	@Test
	void findAllTest() {
		when(this.productRepository.findAll()).thenReturn(productEntityList());
		
		List<ProductOutputDto> productDtoListExpected = productOutputDtoList();
		List<ProductOutputDto> productDtoListActual = this.productService.findAll();
		
		verify(this.productRepository).findAll();
		assertEquals(productDtoListExpected, productDtoListActual);
	}

	@Test
	void findAllNoDataTest() {
		when(this.productRepository.findAll()).thenReturn(new ArrayList<>());
		
		List<ProductOutputDto> productDtoListExpected = new ArrayList<>();
		List<ProductOutputDto> productDtoListActual = this.productService.findAll();
		
		verify(this.productRepository).findAll();
		assertEquals(productDtoListExpected, productDtoListActual);
	}

	@Test
	void findByIdTest() {
		when(this.productRepository.findById(anyLong())).thenReturn(productEntity());
		
		ProductOutputDto productOutputDtoExpected = productOutputDto();
		ProductOutputDto productOutputDtoActual = this.productService.findById(1L);
		
		assertNotNull(productOutputDtoActual);
		assertEquals(productOutputDtoExpected, productOutputDtoActual);
	}
	
	@Test
	void findByThrowGenericExceptionTest() {
		when(this.productRepository.findById(anyLong())).thenThrow(GenericException.class);
		assertThrows(GenericException.class, () -> this.productService.findById(1L));
	}

	@Test
	void update() {
		when(this.productRepository.findById(anyLong())).thenReturn(productEntity());
		when(this.contentRepository.findById(anyLong())).thenReturn(contentEntity());
		when(this.brandRepository.findById(anyLong())).thenReturn(brandEntity());
		
		this.productService.update(1L, productInputDtoUpdate());
		
		verify(this.productRepository).findById(anyLong());
		verify(this.contentRepository).findById(anyLong());
		verify(this.productRepository).save(any(ProductEntity.class));

	}
	
	@Test
	void stockManagementByProductRemoveTest() {
		when(this.productRepository.findById(anyLong())).thenReturn(productEntity());
		
		this.productService.stockManagementByProduct(1L, stockDto("remove"));
		
		verify(this.productRepository).findById(anyLong());
		verify(this.productRepository).save(any(ProductEntity.class));
	}
	
	@Test
	void stockManagementByProductAddTest() {
		when(this.productRepository.findById(anyLong())).thenReturn(productEntity());
		
		this.productService.stockManagementByProduct(1L, stockDto("add"));
		
		verify(this.productRepository).findById(anyLong());
		verify(this.productRepository).save(any(ProductEntity.class));
	}


}

