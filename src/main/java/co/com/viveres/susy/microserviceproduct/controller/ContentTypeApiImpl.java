package co.com.viveres.susy.microserviceproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.viveres.susy.microserviceproduct.api.IContentTypeApi;
import co.com.viveres.susy.microserviceproduct.dto.ContentOutputDto;
import co.com.viveres.susy.microserviceproduct.service.IContentTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/content-type")
public class ContentTypeApiImpl implements IContentTypeApi {

	@Autowired
	private IContentTypeService service;

	@Override
	public ResponseEntity<List<ContentOutputDto>> findAllContent() {
		List<ContentOutputDto> response = this.service.findAllContent();
		return ResponseEntity.ok(response);
	}

}
