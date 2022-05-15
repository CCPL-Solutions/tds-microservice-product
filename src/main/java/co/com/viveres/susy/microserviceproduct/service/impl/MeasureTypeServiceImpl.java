package co.com.viveres.susy.microserviceproduct.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.repository.IMeasureTypeRepository;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;
import co.com.viveres.susy.microserviceproduct.service.IProductService;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;

@Service
public class MeasureTypeServiceImpl implements IMeasureTypeService {
	
	@Autowired
	private IMeasureTypeRepository repository;
	@Autowired
	private IProductService productService;

	@Override
	public MeasureTypeDto create(MeasureTypeDto measureDto) {
		this.validateMeasureTypetAlreadyExist(measureDto);
		MeasureTypeEntity measureEntity = this.mapInMeasureTypeDtoToEntity(measureDto);
		return this.mapOutMeasureTypeEntityToDto(this.persist(measureEntity));
	}

	private void validateMeasureTypetAlreadyExist(MeasureTypeDto measureDto) {
		Optional<MeasureTypeEntity> measureEntity = this.repository
			.findByName(measureDto.getName());
		
		if (measureEntity.isPresent()) {
			throw this.productService.setGenericException(
					ResponseMessages.MEASURETYPE_ALREADY_EXISTS, 
					measureDto.getName());
		}
	}
	
	private MeasureTypeEntity mapInMeasureTypeDtoToEntity(MeasureTypeDto measureDto) {
		MeasureTypeEntity measureEntity = new MeasureTypeEntity();
		measureEntity.setName(measureDto.getName());
		return measureEntity;
	}
	
	private MeasureTypeEntity persist(MeasureTypeEntity measureEntity) {
		return this.repository.save(measureEntity);
	}	
	
	private MeasureTypeDto mapOutMeasureTypeEntityToDto(MeasureTypeEntity measureEntity) {
		MeasureTypeDto measureDto = new MeasureTypeDto();
		measureDto.setId(measureEntity.getId());
		measureDto.setName(measureEntity.getName());
		return measureDto;
	}	

	@Override
	public List<MeasureTypeDto> findAll() {
		List<MeasureTypeEntity> measureEntityList = this.repository.findAll();
		return measureEntityList.stream()
			.map(this::mapOutMeasureTypeEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	public MeasureTypeDto findById(Long measureId) {
		MeasureTypeEntity measureEntity = this.findMeasureTypeEntityById(measureId);
		return this.mapOutMeasureTypeEntityToDto(measureEntity);
	}

	@Override
	public MeasureTypeEntity findMeasureTypeEntityById(Long measureId) {
		return this.repository.findById(measureId).orElseThrow(
			() -> this.productService.setGenericException(
				ResponseMessages.MEASURETYPE_DOES_NOT_EXIST, 
				String.valueOf(measureId)));
	}

}
