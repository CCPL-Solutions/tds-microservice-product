package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.service.IContentService;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;
import co.com.viveres.susy.microserviceproduct.service.mapper.IMapper;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	private IContentRepository repository;

	@Autowired
	private IMeasureTypeService measureTypeService;

	@Autowired
	private IMapper mapper;

	@Override
	public ContentDto create(ContentDto contentDto) {
		MeasureTypeEntity measureTypeEntity = this.measureTypeService.findMeasureTypeEntityById(contentDto.getMeasure().getId());
		this.validateContentAlreadyExist(contentDto, measureTypeEntity);
		ContentEntity contentEntity = this.mapper.mapInContentDtoToEntity(contentDto, measureTypeEntity);
		return this.mapper.mapOutContentEntityToDto(this.persist(contentEntity));
	}

	private void validateContentAlreadyExist(ContentDto contentDto, MeasureTypeEntity measureTypeEntity) {
		Optional<ContentEntity> contentEntity = this.repository.
				findByMeasureTypeAndValue(measureTypeEntity, contentDto.getValue());

		if (contentEntity.isPresent()) {
			throw new BusinessException(ResponseMessages.CONTENT_ALREADY_EXISTS);
		}

	}

	private ContentEntity persist(ContentEntity contentEntity) {
		return this.repository.save(contentEntity);
	}

	@Override
	public List<ContentDto> findAllContent() {
		List<ContentEntity> contentEntityList = this.repository.findAll();
		
		return contentEntityList.stream()
				.map(this.mapper::mapOutContentEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public ContentDto findById(Long contentId) {
		ContentEntity contentEntity = this.findContentEntityById(contentId);
		return this.mapper.mapOutContentEntityToDto(contentEntity);
	}

	@Override
	public ContentEntity findContentEntityById(Long contentId){
		return this.repository.findById(contentId).orElseThrow(
				() -> new NotFoundException(ResponseMessages.CONTENT_DOES_NOT_EXIST));
	}

	@Override
	public void update(Long contentId, ContentDto contentDto) {}

	@Override
	public void delete(Long contentId) {}

}
