package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microserviceproduct.dto.BrandDto;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;

@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	private IBrandRepository repository;

	@Override
	public List<BrandDto> findAllBrands() {
		List<BrandEntity> brandEntityList= this.repository.findAll();
		return brandEntityList.stream()
				.map(this::mapOutBrantEntityToDto)
				.collect(Collectors.toList());
	}
	
	private BrandDto mapOutBrantEntityToDto(BrandEntity brandEntity) {
		BrandDto brandDto = new BrandDto();
		brandDto.setId(brandEntity.getId());
		brandDto.setName(brandEntity.getName());
		return brandDto;
	}

}
