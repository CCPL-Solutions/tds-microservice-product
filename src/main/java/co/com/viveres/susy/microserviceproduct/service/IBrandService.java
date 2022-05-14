package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;

public interface IBrandService {

	BrandDto create(BrandDto brand);

	List<BrandDto> findAllBrands();

	BrandDto findById(Long brandId);

	void update(Long brandId, BrandDto brand);

	void delete(Long brandId);
}
