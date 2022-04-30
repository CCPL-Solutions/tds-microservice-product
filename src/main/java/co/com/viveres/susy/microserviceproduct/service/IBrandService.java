package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microserviceproduct.dto.BrandDto;

public interface IBrandService {
	List<BrandDto> findAllBrands();
}
