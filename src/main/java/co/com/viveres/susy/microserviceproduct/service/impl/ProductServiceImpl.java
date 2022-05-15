package co.com.viveres.susy.microserviceproduct.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import co.com.viveres.susy.microservicecommons.entity.MessageEntity;
import co.com.viveres.susy.microservicecommons.exceptions.GenericException;
import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.repository.IBrandRepository;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;
import co.com.viveres.susy.microserviceproduct.service.IProductService;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private IContentRepository contentRepository;
	@Autowired
	private IMessageRepository messageRepository;
	@Autowired
	private IBrandRepository brandRepository;

	@Override
	public ProductDto create(ProductDto productDto) {
		this.validateProductAlreadyExist(productDto);

		ProductEntity productEntity = new ProductEntity();
		this.mapInProductDtoToEntity(productEntity, productDto);

		ProductEntity newProductEntity = this.productRepository.save(productEntity);
		return this.mapOutProductEntityToDto(newProductEntity);
	}

	private void validateProductAlreadyExist(ProductDto productDto) {

		ContentEntity contentEntity = this.contentRepository.findById(productDto.getContent().getId())
				.orElseThrow(() -> new GenericException(new MessageEntity()));
		
		BrandEntity brandEntity = this.brandRepository.findById(productDto.getBrand().getId())
				.orElseThrow(() -> new GenericException(new MessageEntity()));

		Optional<ProductEntity> productEntity = this.productRepository.findByNameAndBrandAndContent(productDto.getName(),
				brandEntity, contentEntity);

		if (productEntity.isPresent())
			throw this.setGenericException(ResponseMessages.PRODUCT_ALREADY_EXISTS,
					productDto.getName()
					.concat(" ")
					.concat(productDto.getBrand().getName())
					.concat(" de ")
					.concat(String.valueOf(contentEntity.getValue())
							.concat(" ")
							.concat(String.valueOf(contentEntity.getMeasureType().getName())))					
					);
					
	}

	private ProductEntity mapInProductDtoToEntity(ProductEntity productEntity, ProductDto productDto) {

		ContentEntity contentEntity = this.contentRepository.findById(productDto.getContent().getId())
				.orElseThrow(() -> this.setGenericException(ResponseMessages.CONTENT_DOES_NOT_EXIST,
						String.valueOf(productDto.getContent().getId())));
		
		BrandEntity brandEntity = this.brandRepository.findById(productDto.getBrand().getId())
				.orElseThrow(() -> new GenericException(new MessageEntity()));

		productEntity.setContent(contentEntity);
		productEntity.setName(productDto.getName());
		productEntity.setBrand(brandEntity);
		productEntity.setPrice(productDto.getPrice());
		productEntity.setCurrentNumItems(productDto.getCurrentNumItems());
		productEntity.setMinimunStock(productDto.getMinimunStock());
		productEntity.setDescription(productDto.getDescription());

		return productEntity;
	}

	private ProductDto mapOutProductEntityToDto(ProductEntity productEntity) {

		ProductDto productDto = new ProductDto();
		productDto.setId(productEntity.getId());
		productDto.setName(productEntity.getName());
		productDto.setBrand(new BrandDto());
		productDto.getBrand().setId(productEntity.getBrand().getId());
		productDto.getBrand().setName(productEntity.getBrand().getName());
		productDto.setPrice(productEntity.getPrice());
		productDto.setCurrentNumItems(productEntity.getCurrentNumItems());
		productDto.setMinimunStock(productEntity.getMinimunStock());
		productDto.setDescription(productEntity.getDescription());
		productDto.setContent(new ContentDto());
		productDto.getContent().setId(productEntity.getContent().getId());
		productDto.getContent().setMeasure(new MeasureTypeDto());
		productDto.getContent().getMeasure().setId(productEntity.getContent().getMeasureType().getId());
		productDto.getContent().getMeasure().setName(productEntity.getContent().getMeasureType().getName());
		productDto.getContent().setValue(productEntity.getContent().getValue());

		return productDto;
	}

	@Override
	public List<ProductDto> findAll() {			
		List<ProductEntity> productEntityList = this.productRepository.findAll();
		return this.mapOutListProductEntityToDto(productEntityList);
	}

	private List<ProductDto> mapOutListProductEntityToDto(List<ProductEntity> productEntityList) {
		List<ProductDto> productDtoList = new ArrayList<>();
		productEntityList.forEach(productEntity -> {
			ProductDto productDto = this.mapOutProductEntityToDto(productEntity);
			productDtoList.add(productDto);
		});
		return productDtoList;
	}

	@Override
	public ProductDto findById(Long id) {
		ProductEntity productEntity = this.productRepository.findById(id)
				.orElseThrow(() -> this.setGenericException(ResponseMessages.PRODUCT_DOES_NOT_EXIST,String.valueOf(id)));
		return this.mapOutProductEntityToDto(productEntity);
	}

	@Override
	public void update(Long id, ProductDto productDto) {

		ProductEntity productEntity = this.productRepository.findById(id)
				.orElseThrow(() -> this.setGenericException(ResponseMessages.PRODUCT_DOES_NOT_EXIST,String.valueOf(id)));

		this.mapInProductDtoToEntity(productEntity, productDto);
		this.productRepository.save(productEntity);

	}

	@Override
	public void stockManagementByProduct(Long productId, StockDto movement) {

		ProductEntity productEntity = this.productRepository.findById(productId).orElseThrow(
				() -> this.setGenericException(ResponseMessages.PRODUCT_DOES_NOT_EXIST, String.valueOf(productId)));

		if (movement.getType().equals("remove")) {
			productEntity.setCurrentNumItems(productEntity.getCurrentNumItems() - movement.getNumberItems());
		} else {
			productEntity.setCurrentNumItems(productEntity.getCurrentNumItems() + movement.getNumberItems());
		}

		this.productRepository.save(productEntity);

	}
	
	@Override
	public GenericException setGenericException(String responseMessage, String value) {		
		MessageEntity message = this.messageRepository.findById(responseMessage)
				.orElseThrow(NoSuchElementException::new);
		message.setDescripction(String.format(message.getDescripction(), value));
				
		return new GenericException(message);		
	}

}
