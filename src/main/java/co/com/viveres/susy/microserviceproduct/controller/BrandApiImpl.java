package co.com.viveres.susy.microserviceproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.viveres.susy.microserviceproduct.api.IBrandApi;
import co.com.viveres.susy.microserviceproduct.dto.BrandDto;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/brands")
public class BrandApiImpl implements IBrandApi {
	
	@Autowired
	private IBrandService service;

	@Override
	public ResponseEntity<List<BrandDto>> findAllBrands() {
		List<BrandDto> response = this.service.findAllBrands();
		return ResponseEntity.ok(response);
	}

}
