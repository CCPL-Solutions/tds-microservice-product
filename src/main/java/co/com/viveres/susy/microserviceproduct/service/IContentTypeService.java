package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microserviceproduct.dto.ContentOutputDto;

public interface IContentTypeService {

	public List<ContentOutputDto> findAllContent();

}
