package co.com.viveres.susy.microserviceproduct.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.service.IContentTypeService;

@Service
public class ContentTypeServiceImpl implements IContentTypeService {

	@Autowired
	private IContentRepository contentRepository;

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
		contentDto.setMeasure(contentEntity.getMeasureType().getName());
		contentDto.setValue(contentEntity.getValue());
		return contentDto;
	}

}
