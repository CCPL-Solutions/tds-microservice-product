package co.com.viveres.susy.microserviceproduct.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import co.com.viveres.susy.microserviceproduct.dto.BrandDto;

public interface IBrandApi {

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<BrandDto>> findAllBrands();
}
