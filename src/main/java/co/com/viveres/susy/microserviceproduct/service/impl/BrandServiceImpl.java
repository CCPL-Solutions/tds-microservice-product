package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;
import co.com.viveres.susy.microserviceproduct.service.mapper.IMapper;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	private IBrandRepository repository;

	@Autowired
	private IMapper mapper;
	
	@Override
	public BrandDto create(BrandDto brand) {
		this.validateBrandtAlreadyExist(brand);
		BrandEntity brandEntity =  this.mapper.mapInBrandDtoToEntity(brand);
		return this.mapper.mapOutBrantEntityToDto(this.persist(brandEntity));
	}	

	private void validateBrandtAlreadyExist(BrandDto brand) {
		Optional<BrandEntity> brandEntity = this.repository.findByName(brand.getName());
		if (!brandEntity.isEmpty()) {
			throw new BusinessException(ResponseMessages.BRAND_ALREADY_EXISTS);
		}
	}
	
	private BrandEntity persist(BrandEntity brandEntity) {
		return this.repository.save(brandEntity);
	}
	
	@Override
	public List<BrandDto> findAllBrands() {
		List<BrandEntity> brandEntityList= this.repository.findAll();
		return brandEntityList.stream()
				.map(this.mapper::mapOutBrantEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public BrandDto findById(Long brandId) {	
		BrandEntity brandEntity = this.findBrandEntityById(brandId);		
		return this.mapper.mapOutBrantEntityToDto(brandEntity);
	}

	@Override
	public BrandEntity findBrandEntityById(Long brandId) {
		return this.repository.findById(brandId).orElseThrow(() ->
				new NotFoundException(ResponseMessages.BRAND_DOES_NOT_EXIST));
	}

	@Override
	public void update(Long brandId, BrandDto brand) {
		BrandEntity brandEntity = this.findBrandEntityById(brandId);
		brandEntity.setId(brandId);
		brandEntity.setName(brand.getName());
		this.persist(brandEntity);
	}

	@Override
	public void delete(Long brandId) {}

}
