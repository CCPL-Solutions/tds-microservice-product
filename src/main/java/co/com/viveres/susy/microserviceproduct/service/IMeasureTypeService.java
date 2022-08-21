package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;

public interface IMeasureTypeService {
	
	MeasureTypeDto create(MeasureTypeDto measureTypeDto);
	
	List<MeasureTypeDto> findAll();
	
	MeasureTypeDto findById(Long measureId);
	
	void update(Long measureId, MeasureTypeDto measureTypeDto);

	MeasureTypeEntity findMeasureTypeEntityById(Long measureId);

}
