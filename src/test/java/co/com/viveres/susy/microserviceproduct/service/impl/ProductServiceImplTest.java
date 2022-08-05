package co.com.viveres.susy.microserviceproduct.service.impl;

import static co.com.viveres.susy.microserviceproduct.DummyMock.contentEntity;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productEntity;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productInputDto;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productInputDtoUpdate;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productOutputDto;
import static co.com.viveres.susy.microserviceproduct.DummyMock.stockDto;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;
import co.com.viveres.susy.microserviceproduct.service.impl.ProductServiceImpl;

//@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	/*@Mock
	private IProductRepository productRepository;
	@Mock
	private IContentRepository contentRepository;
	@Mock
	private IBrandRepository brandRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void createProductAlreadyExistTest() {
		when(this.contentRepository.findById(anyLong())).thenReturn(contentEntity());					
		assertThrows(BusinessException.class, () -> this.productService.create(productInputDto()));
		verify(this.contentRepository).findById(anyLong());		

	}

	@Test
	void createOk() {
		when(this.contentRepository.findById(anyLong())).thenReturn(contentEntity());
		when(this.brandRepository.findById(anyLong())).thenReturn(brandEntity());
		when(this.productRepository.findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class))).thenReturn(Optional.empty());
		when(this.productRepository.save(any(ProductEntity.class))).thenReturn(productEntity().orElseThrow());

		ProductDto ProductDtoExpected = productOutputDto();
		ProductDto ProductDtoActual = this.productService.create(productInputDto());
		
		verify(this.contentRepository, times(2)).findById(anyLong());
		verify(this.productRepository).findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class));
		
		assertNotNull(ProductDtoActual);
		assertEquals(ProductDtoExpected, ProductDtoActual);			
	}
	
	/*@Test
	void findAllTest() {
		when(this.productRepository.findAll()).thenReturn(productEntityList());
		
		List<ProductDto> productDtoListExpected = productOutputDtoList();
		List<ProductDto> productDtoListActual = this.productService.findAll(0, 10);
		
		verify(this.productRepository).findAll();
		assertEquals(productDtoListExpected, productDtoListActual);
	}*/

	/*@Test
	void findAllNoDataTest() {
		when(this.productRepository.findAll()).thenReturn(new ArrayList<>());
		
		List<ProductDto> productDtoListExpected = new ArrayList<>();
		List<ProductDto> productDtoListActual = this.productService.findAll();
		
		verify(this.productRepository).findAll();
		assertEquals(productDtoListExpected, productDtoListActual);
	}

	@Test
	void findByIdTest() {
		when(this.productRepository.findById(anyLong())).thenReturn(productEntity());
		
		ProductDto ProductDtoExpected = productOutputDto();
		ProductDto ProductDtoActual = this.productService.findById(1L);
		
		assertNotNull(ProductDtoActual);
		assertEquals(ProductDtoExpected, ProductDtoActual);
	}
	
	@Test
	void findByThrowGenericExceptionTest() {
		when(this.productRepository.findById(anyLong())).thenThrow(BusinessException.class);
		assertThrows(BusinessException.class, () -> this.productService.findById(1L));
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
	}*/

}

