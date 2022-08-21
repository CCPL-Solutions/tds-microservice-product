package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;

public interface IContentService {
	
	ContentDto create(ContentDto contentDto);
	List<ContentDto> findAllContent();
	ContentDto findById(Long contentId);
	void update(Long contentId, ContentDto contentDto);
	void delete(Long contentId);

	ContentEntity findContentEntityById(Long contentId);
}
