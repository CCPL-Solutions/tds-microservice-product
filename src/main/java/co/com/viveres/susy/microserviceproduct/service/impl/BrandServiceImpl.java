package co.com.viveres.susy.microserviceproduct.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.entity.MessageEntity;
import co.com.viveres.susy.microservicecommons.exception.GenericException;
import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;

@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	private IBrandRepository repository;
	@Autowired
	private IMessageRepository messageRepository;
	
	@Override
	public BrandDto create(BrandDto brand) {
		validateBrandtAlreadyExist(brand);
		BrandEntity brandEntity =  this.mapInBrandDtoToEntity(brand);
		return this.mapOutBrantEntityToDto(this.persist(brandEntity));
	}	

	private void validateBrandtAlreadyExist(BrandDto brand) {
		Optional<BrandEntity> brandEntity = this.repository.findByName(brand.getName());
		if (!brandEntity.isEmpty()) {
			throw this.setGenericException(ResponseMessages.BRAND_ALREADY_EXISTS, brand.getName());
		}
	}
	
	private BrandEntity mapInBrandDtoToEntity(BrandDto brand) {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setName(brand.getName());
		return brandEntity;
	}
	
	private BrandEntity persist(BrandEntity brandEntity) {
		return this.repository.save(brandEntity);
	}
	
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

	@Override
	public BrandDto findById(Long brandId) {	
		BrandEntity brandEntity = this.findBrandEntityById(brandId);		
		return this.mapOutBrantEntityToDto(brandEntity);
	}

	private BrandEntity findBrandEntityById(Long brandId) {
		return this.repository.findById(brandId)
			.orElseThrow(() -> this.setGenericException(
					ResponseMessages.BRAND_DOES_NOT_EXIST, 
					String.valueOf(brandId)));
	}

	@Override
	public void update(Long brandId, BrandDto brand) {
		BrandEntity brandEntity = this.findBrandEntityById(brandId);
		brandEntity.setId(brandId);
		brandEntity.setName(brand.getName());
		this.persist(brandEntity);
	}

	@Override
	public void delete(Long brandId) {
		// No impl		
	}
	
	private GenericException setGenericException(String responseMessage, String value) {		
		MessageEntity message = this.messageRepository
				.findById(responseMessage)
				.orElseThrow(NoSuchElementException::new);
		
		message.setDescripction(String.format(message.getDescripction(), value));				
		return new GenericException(message);		
	}	

}
