package co.com.viveres.susy.microserviceproduct.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.service.IContentTypeService;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;
import co.com.viveres.susy.microserviceproduct.service.IProductService;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;

@Service
public class ContentTypeServiceImpl implements IContentTypeService {

	@Autowired
	private IContentRepository contentRepository;
	@Autowired
	private IMeasureTypeService measureTypeService;
	@Autowired
	private IProductService productService;

	@Override
	public List<ContentDto> findAllContent() {
		List<ContentEntity> contentEntityList = this.contentRepository.findAll();		
		
		return contentEntityList.stream()
				.map(this::mapOutContentEntityToDto)
				.collect(Collectors.toList());
	}
	
	private ContentDto mapOutContentEntityToDto(ContentEntity contentEntity) {
		ContentDto contentDto = new ContentDto();
		contentDto.setId(contentEntity.getId());
		contentDto.setMeasure(new MeasureTypeDto());
		contentDto.getMeasure().setId(contentEntity.getMeasureType().getId());
		contentDto.getMeasure().setName(contentEntity.getMeasureType().getName());		
		contentDto.setValue(contentEntity.getValue());
		return contentDto;
	}

	@Override
	public ContentDto create(ContentDto contentDto) {
		this.validateContentAlreadyExist(contentDto);
		ContentEntity contentEntity = this.mapInContentDtoToEntity(contentDto);
		return this.mapOutContentEntityToDto(this.persist(contentEntity));
	}

	private void validateContentAlreadyExist(ContentDto contentDto) {
		MeasureTypeEntity measureTypeEntity = this.measureTypeService
				.findMeasureTypeEntityById(contentDto.getMeasure().getId());
		
		Optional<ContentEntity> contentEntity = this.contentRepository.
				findByMeasureTypeAndValue(measureTypeEntity, contentDto.getValue());
		
		if (contentEntity.isPresent()) {
			throw this.productService.setGenericException(
					ResponseMessages.CONTENT_ALREADY_EXISTS, null);
		}
		
	}
	
	private ContentEntity mapInContentDtoToEntity(ContentDto contentDto) {
		MeasureTypeEntity measureTypeEntity = this.measureTypeService
				.findMeasureTypeEntityById(contentDto.getMeasure().getId());
		
		ContentEntity contentEntity= new ContentEntity();
		contentEntity.setValue(contentDto.getValue());
		contentEntity.setMeasureType(measureTypeEntity);
		return contentEntity;
	}
	
	private ContentEntity persist(ContentEntity contentEntity) {
		return this.contentRepository.save(contentEntity);
	}

}
