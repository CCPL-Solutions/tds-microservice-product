package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;

public interface IContentTypeService {

	public List<ContentDto> findAllContent();

}
