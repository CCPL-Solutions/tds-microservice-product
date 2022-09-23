package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microservicecommons.util.ResponseMessages;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;
import co.com.viveres.susy.microserviceproduct.service.mapper.impl.MapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static co.com.viveres.susy.microserviceproduct.DummyMock.contentEntity;
import static co.com.viveres.susy.microserviceproduct.DummyMock.contentEntityList;
import static co.com.viveres.susy.microserviceproduct.DummyMock.contentInputDto;
import static co.com.viveres.susy.microserviceproduct.DummyMock.measureTypeOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ContentServiceImplTest {

    @Mock
    private IContentRepository repository;

    @Mock
    private IMeasureTypeService measureTypeService;

    @Spy
    private MapperImpl mapper;

    @InjectMocks
    private ContentServiceImpl service;

    @Test
    void createOkTest() {
        // Given
        MeasureTypeEntity measureTypeEntity = measureTypeOut();
        Optional<ContentEntity> empty = Optional.empty();
        ContentEntity contentEntity = contentEntity().orElseThrow();
        ContentDto contentInputDtoDto = contentInputDto();
        // When
        when(this.measureTypeService.findMeasureTypeEntityById(anyLong())).thenReturn(measureTypeEntity);
        when(this.repository.findByMeasureTypeAndValue(any(MeasureTypeEntity.class), anyDouble())).thenReturn(empty);
        when(this.repository.save(any(ContentEntity.class))).thenReturn(contentEntity);
        ContentDto contentDtoActual = this.service.create(contentInputDtoDto);
        // Then
        verify(this.measureTypeService).findMeasureTypeEntityById(anyLong());
        verify(this.repository).findByMeasureTypeAndValue(any(MeasureTypeEntity.class), anyDouble());
        verify(this.repository).save(any(ContentEntity.class));
        assertNotNull(contentDtoActual);
        assertEquals(1L, contentDtoActual.getId());
        assertEquals(contentInputDtoDto.getMeasure().getId(), contentDtoActual.getMeasure().getId());
        assertEquals(contentInputDtoDto.getMeasure().getName(), contentDtoActual.getMeasure().getName());
        assertEquals(contentInputDtoDto.getValue(), contentDtoActual.getValue());
    }

    @Test
    void createAlreadyExistTest() {
        // Given
        MeasureTypeEntity measureTypeEntity = measureTypeOut();
        Optional<ContentEntity> contentEntity = contentEntity();
        ContentDto contentInputDtoDto = contentInputDto();
        // When
        when(this.measureTypeService.findMeasureTypeEntityById(anyLong())).thenReturn(measureTypeEntity);
        when(this.repository.findByMeasureTypeAndValue(any(MeasureTypeEntity.class), anyDouble())).thenReturn(contentEntity);
        BusinessException exception = assertThrows(BusinessException.class, () -> this.service.create(contentInputDtoDto));
        // Then
        verify(this.measureTypeService).findMeasureTypeEntityById(anyLong());
        verify(this.repository).findByMeasureTypeAndValue(any(MeasureTypeEntity.class), anyDouble());
        assertNotNull(exception);
        assertEquals(exception.getMessage(), ResponseMessages.CONTENT_ALREADY_EXISTS);
    }

    @Test
    void findAllContentTest() {
        // Given
        List<ContentEntity> contentEntityList = contentEntityList();
        // When
        when(this.repository.findAll()).thenReturn(contentEntityList);
        List<ContentDto> contentDtoList = this.service.findAllContent();
        // Then
        assertFalse(contentDtoList.isEmpty());
        assertEquals(3, contentDtoList.size());
    }

    @Test
    void findByIdTest() {
        // Given
        Optional<ContentEntity> contentEntity = contentEntity();
        Long contentId = 1L;
        // When
        when(this.repository.findById(anyLong())).thenReturn(contentEntity);
        ContentDto contentDtoActual = this.service.findById(contentId);
        // Then
        verify(this.repository).findById(anyLong());
        assertNotNull(contentDtoActual);
        assertEquals(1L, contentDtoActual.getId());
        assertEquals(500d, contentDtoActual.getValue());
        assertEquals(1L, contentDtoActual.getMeasure().getId());
        assertEquals("gramos", contentDtoActual.getMeasure().getName());
    }

    @Test
    void findByIdNotFoundExceptionTest() {
        // Given
        Long contentId = 1L;
        Optional<ContentEntity> contentEntity = Optional.empty();
        // When
        when(this.repository.findById(anyLong())).thenReturn(contentEntity);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> this.service.findById(contentId));
        // Then
        verify(this.repository).findById(anyLong());
        assertNotNull(exception);
        assertEquals(exception.getMessage(), ResponseMessages.CONTENT_DOES_NOT_EXIST);
    }

    @Test
    void updateTest() {
        // Given
        // When
        // Then
    }

    @Test
    void deleteTest() {
        // Given
        // When
        // Then
    }
}