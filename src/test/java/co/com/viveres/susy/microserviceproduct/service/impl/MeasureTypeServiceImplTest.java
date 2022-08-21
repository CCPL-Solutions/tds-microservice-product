package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microservicecommons.util.ResponseMessages;
import co.com.viveres.susy.microserviceproduct.DummyMock;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.repository.IMeasureTypeRepository;
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

@ExtendWith(MockitoExtension.class)
class MeasureTypeServiceImplTest {

    @Mock
    private IMeasureTypeRepository repository;

    @Spy
    private MapperImpl mapper;

    @InjectMocks
    private MeasureTypeServiceImpl service;

    @Test
    void createMeasureTypeNotExist() {
        //Given
        Optional<MeasureTypeEntity> measureTypeEntityOptional = Optional.empty();
        MeasureTypeEntity measureTypeOut = DummyMock.measureTypeOut();
        MeasureTypeDto measureDtoIn = DummyMock.measureTypeDtoIn();
        //When
        Mockito.when(this.repository.findByName(ArgumentMatchers.anyString())).thenReturn(measureTypeEntityOptional);
        Mockito.when(this.repository.save(ArgumentMatchers.any(MeasureTypeEntity.class))).thenReturn(measureTypeOut);
        MeasureTypeDto measureDtoOut = this.service.create(measureDtoIn);
        //Then
        Mockito.verify(this.repository).findByName(ArgumentMatchers.anyString());
        Mockito.verify(this.repository).save(ArgumentMatchers.any(MeasureTypeEntity.class));
        Assertions.assertNotNull(measureDtoOut);
        Assertions.assertEquals(measureTypeOut.getId(), measureDtoOut.getId());
        Assertions.assertEquals(measureTypeOut.getName(), measureDtoOut.getName());
    }

    @Test
    void createMeasureTypeAlreadyExist() {
        //Given
        Optional<MeasureTypeEntity> measureTypeEntityOptional = Optional.of(DummyMock.measureTypeOut());
        MeasureTypeDto measureDtoIn = DummyMock.measureTypeDtoIn();
        //When
        Mockito.when(this.repository.findByName(ArgumentMatchers.anyString())).thenReturn(measureTypeEntityOptional);
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> this.service.create(measureDtoIn));
        //Then
        Mockito.verify(this.repository).findByName(ArgumentMatchers.anyString());
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(ResponseMessages.MEASURETYPE_ALREADY_EXISTS, exception.getMessage());
    }

    @Test
    void findAll() {
        //Given
        List<MeasureTypeEntity> measureEntityList = DummyMock.measureEntityList();
        List<MeasureTypeDto> measureDtoList = DummyMock.measureDtoListOut();
        //When
        Mockito.when( this.repository.findAll()).thenReturn(measureEntityList);
        List<MeasureTypeDto> measureTypeDtoList = this.service.findAll();
        //Then
        Mockito.verify(this.repository).findAll();
        Assertions.assertFalse(measureTypeDtoList.isEmpty());
        Assertions.assertEquals(measureDtoList.size(), measureTypeDtoList.size());
        Assertions.assertEquals(measureDtoList.get(0).getId(), measureTypeDtoList.get(0).getId());
        Assertions.assertEquals(measureDtoList.get(0).getName(), measureTypeDtoList.get(0).getName());
        Assertions.assertEquals(measureDtoList.get(1).getId(), measureTypeDtoList.get(1).getId());
        Assertions.assertEquals(measureDtoList.get(1).getName(), measureTypeDtoList.get(1).getName());
        Assertions.assertEquals(measureDtoList.get(2).getId(), measureTypeDtoList.get(2).getId());
        Assertions.assertEquals(measureDtoList.get(2).getName(), measureTypeDtoList.get(2).getName());

    }

    @Test
    void findById() {
        //Given
        Optional<MeasureTypeEntity> measureTypeEntity = Optional.of(DummyMock.measureTypeOut());
        MeasureTypeDto measureTypeDtoExpected = DummyMock.measureTypeDtoIn();
        //When
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(measureTypeEntity);
        MeasureTypeDto measureTypeDtoActual = this.service.findById(1L);
        //Then
        Mockito.verify(this.repository).findById(ArgumentMatchers.anyLong());
        Assertions.assertNotNull(measureTypeDtoActual);
        Assertions.assertEquals(measureTypeDtoExpected.getId(), measureTypeDtoActual.getId());
        Assertions.assertEquals(measureTypeDtoExpected.getName(), measureTypeDtoActual.getName());
    }

    @Test
    void findByIdNotFoundException() {
        //Given
        MeasureTypeDto measureTypeDtoExpected = DummyMock.measureTypeDtoIn();
        //When
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, ()-> this.service.findById(1L));
        //Then
        Mockito.verify(this.repository).findById(ArgumentMatchers.anyLong());
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(ResponseMessages.MEASURETYPE_DOES_NOT_EXIST, exception.getMessage());
    }

    @Test
    void update() {
        //Given
        Optional<MeasureTypeEntity> measureTypeEntity = Optional.of(DummyMock.measureTypeOut());
        MeasureTypeDto measureTypeDtoIn = DummyMock.measureTypeDtoIn();
        //When
        Mockito.when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(measureTypeEntity);
        this.service.update(1L, measureTypeDtoIn);
        //Then
        Mockito.verify(this.repository).findById(ArgumentMatchers.anyLong());
        Mockito.verify(this.repository).save(ArgumentMatchers.any(MeasureTypeEntity.class));
    }
}