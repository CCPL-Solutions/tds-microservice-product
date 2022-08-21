package co.com.viveres.susy.microserviceproduct.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microserviceproduct.api.IContentTypeApi;
import co.com.viveres.susy.microserviceproduct.service.IContentService;

@RestController
@RequestMapping("/v1/content-type")
public class ContentTypeApiImpl implements IContentTypeApi {

	@Autowired
	private IContentService service;

	@Override
	public ResponseEntity<List<ContentDto>> findAllContent() {
		List<ContentDto> response = this.service.findAllContent();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<ContentDto> create(@Valid ContentDto brand) {
		ContentDto response = this.service.create(brand);
		return this.buildCreatResponse(response);
	}
	
	private ResponseEntity<ContentDto> buildCreatResponse(
			ContentDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{content-id}")
				.buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}

}
