package co.com.viveres.susy.microserviceproduct.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microserviceproduct.api.IBrandApi;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/brands")
public class BrandApiImpl implements IBrandApi {
	
	@Autowired
	private IBrandService service;

	@Override
	public ResponseEntity<BrandDto> create(BrandDto brand) {
		BrandDto response = this.service.create(brand);
		return this.buildCreatResponse(response);
	}

	@Override
	public ResponseEntity<List<BrandDto>> findAllBrands() {
		List<BrandDto> response = this.service.findAllBrands();
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<BrandDto> findById(Long brandId) {
		BrandDto response = this.service.findById(brandId);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Void> update(Long brandId, BrandDto brand) {
		this.service.update(brandId, brand);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> delete(Long brandId) {
		this.service.delete(brandId);
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<BrandDto> buildCreatResponse(
			BrandDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{brand-id}")
				.buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}

}
