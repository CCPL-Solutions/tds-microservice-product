package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.repository.IMeasureTypeRepository;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;
import co.com.viveres.susy.microserviceproduct.service.mapper.IMapper;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeasureTypeServiceImpl implements IMeasureTypeService {
	
	@Autowired
	private IMeasureTypeRepository repository;

	@Autowired
	private IMapper mapper;

	@Override
	public MeasureTypeDto create(MeasureTypeDto measureDto) {
		this.validateMeasureTypeAlreadyExist(measureDto);
		MeasureTypeEntity measureEntity = this.mapper.mapInMeasureTypeDtoToEntity(measureDto);
		return this.mapper.mapOutMeasureTypeEntityToDto(this.persist(measureEntity));
	}

	private void validateMeasureTypeAlreadyExist(MeasureTypeDto measureDto) {
		Optional<MeasureTypeEntity> measureEntity = this.repository
			.findByName(measureDto.getName());
		
		if (measureEntity.isPresent()) {
			throw new BusinessException(ResponseMessages.MEASURETYPE_ALREADY_EXISTS);
		}
	}
	
	private MeasureTypeEntity persist(MeasureTypeEntity measureEntity) {
		return this.repository.save(measureEntity);
	}

	@Override
	public List<MeasureTypeDto> findAll() {
		List<MeasureTypeEntity> measureEntityList = this.repository.findAll();
		return measureEntityList.stream()
			.map(this.mapper::mapOutMeasureTypeEntityToDto)
			.collect(Collectors.toList());
	}

	@Override
	public MeasureTypeDto findById(Long measureId) {
		MeasureTypeEntity measureEntity = this.findMeasureTypeEntityById(measureId);
		return this.mapper.mapOutMeasureTypeEntityToDto(measureEntity);
	}

	@Override
	public MeasureTypeEntity findMeasureTypeEntityById(Long measureId) {
		return this.repository.findById(measureId).orElseThrow(
				() -> new NotFoundException(ResponseMessages.MEASURETYPE_DOES_NOT_EXIST));
	}


	@Override
	public void update(Long measureId, MeasureTypeDto measureTypeDto) {
		MeasureTypeEntity measureEntity = this.findMeasureTypeEntityById(measureId);
		measureEntity.setId(measureId);
		measureEntity.setName(measureTypeDto.getName());
		this.persist(measureEntity);
	}

}
