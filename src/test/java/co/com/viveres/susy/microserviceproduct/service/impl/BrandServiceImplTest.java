package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microservicecommons.util.ResponseMessages;
import co.com.viveres.susy.microserviceproduct.DummyMock;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.service.mapper.impl.MapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private IBrandRepository repository;

    @Spy
    private MapperImpl mapper;

    @InjectMocks
    private BrandServiceImpl service;

    @Test
    void create() {
        // Given
        BrandEntity brandEntity = DummyMock.brandEntityCreated();
        BrandDto brandDto = DummyMock.brandDtoIn();
        // When
        Mockito.when(this.repository.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
        Mockito.when(this.repository.save(ArgumentMatchers.any(BrandEntity.class))).thenReturn(brandEntity);
        BrandDto brandDtoCreated = this.service.create(brandDto);
        // Then
        Mockito.verify(this.repository).findByName(ArgumentMatchers.anyString());
        Mockito.verify(this.repository).save(ArgumentMatchers.any(BrandEntity.class));
        Assertions.assertNotNull(brandDtoCreated);
        Assertions.assertEquals(1L, brandDtoCreated.getId());
        Assertions.assertEquals("Diana", brandDtoCreated.getName());
    }

    @Test
    void createBusinessException() {
        // Given
        BrandEntity brandEntity = DummyMock.brandEntityCreated();
        BrandDto brandDto = DummyMock.brandDtoIn();
        // When
        Mockito.when(this.repository.findByName(ArgumentMatchers.anyString())).thenReturn(Optional.of(brandEntity));
        BusinessException exception = assertThrows(BusinessException.class, () -> this.service.create(brandDto));
        // Then
        Mockito.verify(this.repository).findByName(ArgumentMatchers.anyString());
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(ResponseMessages.BRAND_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void findAllBrands() {
        // Given
        List<BrandEntity> brandEntityList = DummyMock.brandEntityList();
        List<BrandDto> brandDtoListExpected = DummyMock.brandDtoList();
        // When
        Mockito.when(this.repository.findAll()).thenReturn(brandEntityList);
        List<BrandDto> brandDtoListActual = this.service.findAllBrands();
        // Then
        Mockito.verify(this.repository).findAll();
        Assertions.assertNotNull(brandDtoListActual);
        Assertions.assertEquals(brandDtoListExpected.size(), brandDtoListActual.size());
        Assertions.assertEquals(brandDtoListExpected.get(0).getId(), brandDtoListActual.get(0).getId());
        Assertions.assertEquals(brandDtoListExpected.get(0).getName(), brandDtoListActual.get(0).getName());
        Assertions.assertEquals(brandDtoListExpected.get(1).getId(), brandDtoListActual.get(1).getId());
        Assertions.assertEquals(brandDtoListExpected.get(1).getName(), brandDtoListActual.get(1).getName());
        Assertions.assertEquals(brandDtoListExpected.get(2).getId(), brandDtoListActual.get(2).getId());
        Assertions.assertEquals(brandDtoListExpected.get(2).getName(), brandDtoListActual.get(2).getName());
    }

    @Test
    void findById() {
        // Given
        BrandEntity brandEntity = DummyMock.brandEntityCreated();
        Long brandId = 1L;
        // When
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(brandEntity));
        BrandDto brandDtoActual = this.service.findById(brandId);
        // Then
        Mockito.verify(this.repository).findById(ArgumentMatchers.anyLong());
        Assertions.assertNotNull(brandDtoActual);
        Assertions.assertEquals(1L, brandDtoActual.getId());
        Assertions.assertEquals("Diana", brandDtoActual.getName());
    }

    @Test
    void findByIdNotFoundExceptionTest() {
        // Given
        BrandEntity brandEntity = DummyMock.brandEntityCreated();
        Long brandId = 1L;
        // When
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> this.service.findById(brandId));
        // Then
        Mockito.verify(this.repository).findById(ArgumentMatchers.anyLong());
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(ResponseMessages.BRAND_DOES_NOT_EXIST, exception.getMessage());
    }

    @Test
    void update() {
        // Given
        BrandEntity brandEntity = DummyMock.brandEntityCreated();
        BrandDto brandDto = DummyMock.brandDtoIn();
        Long brandId = 1L;
        // When
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(brandEntity));
        this.service.update(brandId, brandDto);
        // Then
        Mockito.verify(this.repository).findById(ArgumentMatchers.anyLong());
        Mockito.verify(this.repository).save(ArgumentMatchers.any(BrandEntity.class));
    }

    @Test
    void delete() {
        // Given
        // When
        // Then
    }
}