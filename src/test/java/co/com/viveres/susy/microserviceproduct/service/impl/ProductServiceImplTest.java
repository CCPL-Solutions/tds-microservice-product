package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microservicecommons.util.ResponseMessages;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;
import co.com.viveres.susy.microserviceproduct.service.IContentService;
import co.com.viveres.susy.microserviceproduct.service.mapper.impl.MapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static co.com.viveres.susy.microserviceproduct.DummyMock.brandEntityCreated;
import static co.com.viveres.susy.microserviceproduct.DummyMock.contentEntity;
import static co.com.viveres.susy.microserviceproduct.DummyMock.getProductEntityPage;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productEntity;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productEntityTwo;
import static co.com.viveres.susy.microserviceproduct.DummyMock.productInputDto;
import static co.com.viveres.susy.microserviceproduct.DummyMock.stockDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IContentService contentService;

    @Mock
    private IBrandService brandService;

    @Spy
    private MapperImpl mapper;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    void create() {
        // Given
        ContentEntity contentEntity = contentEntity().orElseThrow();
        BrandEntity brandEntity = brandEntityCreated();
        Optional<ProductEntity> empty = Optional.empty();
        ProductEntity productEntity = productEntity().orElseThrow();
        ProductDto productDto = productInputDto();
        // When
        when(this.contentService.findContentEntityById(anyLong())).thenReturn(contentEntity);
        when(this.brandService.findBrandEntityById(anyLong())).thenReturn(brandEntity);
        when(this.productRepository.findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class))).thenReturn(empty);
        when(this.productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        ProductDto productDtoActual = this.service.create(productDto);
        // Then
        verify(this.contentService).findContentEntityById(anyLong());
        verify(this.brandService).findBrandEntityById(anyLong());
        verify(this.productRepository).findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class));
        verify(this.productRepository).save(any(ProductEntity.class));
        assertNotNull(productDtoActual);
    }

    @Test
    void createBusinessException() {
        // Given
        ContentEntity contentEntity = contentEntity().orElseThrow();
        BrandEntity brandEntity = brandEntityCreated();
        Optional<ProductEntity> empty = productEntity();
        ProductDto productDto = productInputDto();
        // When
        when(this.contentService.findContentEntityById(anyLong())).thenReturn(contentEntity);
        when(this.brandService.findBrandEntityById(anyLong())).thenReturn(brandEntity);
        when(this.productRepository.findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class))).thenReturn(empty);
        BusinessException exception = assertThrows(BusinessException.class, () -> this.service.create(productDto));
        // Then
        verify(this.contentService).findContentEntityById(anyLong());
        verify(this.brandService).findBrandEntityById(anyLong());
        verify(this.productRepository).findByNameAndBrandAndContent(anyString(), any(BrandEntity.class), any(ContentEntity.class));
        assertNotNull(exception);
        assertEquals(exception.getMessage(), ResponseMessages.PRODUCT_ALREADY_EXISTS);
    }

    @Test
    void findAllWithProductNameFilter() {
        // Given
        int page = 0;
        int size= 3;
        String sort = "name";
        String productName = "Arroz";
        String productBran = null;
        Page<ProductEntity> productEntityPage = getProductEntityPage();
        // When
        when(this.productRepository.findByNameContainingAndIsActive(anyString(), any(Pageable.class), anyBoolean())).thenReturn(productEntityPage);
        Page<ProductDto> productDtoPage = this.service.findAll(page, size, sort, productName, productBran);
        // Then
        verify(this.productRepository).findByNameContainingAndIsActive(anyString(), any(Pageable.class), anyBoolean());
        assertNotNull(productDtoPage);
        assertEquals(3, productDtoPage.getTotalElements());
    }

    @Test
    void findAllWithProductBranFilter() {
        // Given
        int page = 0;
        int size= 3;
        String sort = "name";
        String productName = null;
        String productBran = "Diana";
        Page<ProductEntity> productEntityPage = getProductEntityPage();
        // When
        when(this.productRepository.findByBrandNameContainingAndIsActive(anyString(), any(Pageable.class), anyBoolean())).thenReturn(productEntityPage);
        Page<ProductDto> productDtoPage = this.service.findAll(page, size, sort, productName, productBran);
        // Then
        verify(this.productRepository).findByBrandNameContainingAndIsActive(anyString(), any(Pageable.class), anyBoolean());
        assertNotNull(productDtoPage);
        assertEquals(3, productDtoPage.getTotalElements());
    }

    @Test
    void findAll() {
        // Given
        int page = 0;
        int size= 3;
        String sort = "name";
        String productName = null;
        String productBran = null;
        Page<ProductEntity> productEntityPage = getProductEntityPage();
        // When
        when(this.productRepository.findAllByIsActive(any(Pageable.class), anyBoolean())).thenReturn(productEntityPage);
        Page<ProductDto> productDtoPage = this.service.findAll(page, size, sort, productName, productBran);
        // Then
        verify(this.productRepository).findAllByIsActive(any(Pageable.class), anyBoolean());
        assertNotNull(productDtoPage);
        assertEquals(3, productDtoPage.getTotalElements());
    }

    @Test
    void findByIdOk() {
        // Given
        Long productId = 1L;
        ProductEntity productEntity = productEntityTwo();
        // When
        when(this.productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        ProductDto productDto = this.service.findById(productId);
        // Then
        verify(this.productRepository).findById(anyLong());
        assertNotNull(productDto);
    }

    @Test
    void findByIdNotFoundException() {
        // Given
        Long productId = 1L;
        // When
        when(this.productRepository.findById(anyLong())).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> this.service.findById(productId));
        // Then
        verify(this.productRepository).findById(anyLong());
        assertNotNull(exception);
        assertEquals(ResponseMessages.PRODUCT_DOES_NOT_EXIST, exception.getMessage());
    }

    @Test
    void update() {
        // Given
        Long productId = 1L;
        ProductDto productDto = productInputDto();
        ProductEntity productEntity = productEntityTwo();
        ContentEntity contentEntity = contentEntity().orElseThrow();
        BrandEntity brandEntity = brandEntityCreated();
        // When
        when( this.productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        when(this.contentService.findContentEntityById(anyLong())).thenReturn(contentEntity);
        when(this.brandService.findBrandEntityById(anyLong())).thenReturn(brandEntity);
        this.service.update(productId, productDto);
        // Then
        verify( this.productRepository).findById(anyLong());
        verify(this.contentService).findContentEntityById(anyLong());
        verify(this.brandService).findBrandEntityById(anyLong());
        verify(this.productRepository).save(any(ProductEntity.class));
    }

    @Test
    void stockManagementByProductRemoveTest() {
        // Given
        Long productId = 1L;
        StockDto movement = stockDto("remove");
        ProductEntity productEntity = productEntityTwo();
        // When
        when( this.productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        this.service.stockManagementByProduct(productId, movement);
        // Then
        verify( this.productRepository).findById(anyLong());
        verify(this.productRepository).save(any(ProductEntity.class));
    }

    @Test
    void stockManagementByProductAddTest() {
        // Given
        Long productId = 1L;
        StockDto movement = stockDto("add");
        ProductEntity productEntity = productEntityTwo();
        // When
        when( this.productRepository.findById(anyLong())).thenReturn(Optional.of(productEntity));
        this.service.stockManagementByProduct(productId, movement);
        // Then
        verify( this.productRepository).findById(anyLong());
        verify(this.productRepository).save(any(ProductEntity.class));
    }
}