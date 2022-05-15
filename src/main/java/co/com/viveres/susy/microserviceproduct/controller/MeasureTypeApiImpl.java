package co.com.viveres.susy.microserviceproduct.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microserviceproduct.api.IMeasureTypeApi;
import co.com.viveres.susy.microserviceproduct.service.IMeasureTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/measure-type")
public class MeasureTypeApiImpl implements IMeasureTypeApi {
	
	@Autowired
	private IMeasureTypeService service;

	@Override
	public ResponseEntity<MeasureTypeDto> create(MeasureTypeDto measureDto) {
		MeasureTypeDto response = this.service.create(measureDto);
		return this.buildCreatResponse(response);
	}

	@Override
	public ResponseEntity<List<MeasureTypeDto>> findAll() {
		List<MeasureTypeDto> response = this.service.findAll();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<MeasureTypeDto> findById(Long measureId) {
		MeasureTypeDto response = this.service.findById(measureId);
		return ResponseEntity.ok(response);
	}

	private ResponseEntity<MeasureTypeDto> buildCreatResponse(
			MeasureTypeDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{measure-type-id}")
				.buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}

}
