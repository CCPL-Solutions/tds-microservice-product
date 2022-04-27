package co.com.viveres.susy.microserviceproduct.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.viveres.susy.microservicecommons.entity.MessageEntity;
import co.com.viveres.susy.microservicecommons.exceptions.GenericException;
import co.com.viveres.susy.microservicecommons.repository.IMessageRepository;
import co.com.viveres.susy.microserviceproduct.dto.ContentOutputDto;
import co.com.viveres.susy.microserviceproduct.dto.ProductInputDto;
import co.com.viveres.susy.microserviceproduct.dto.ProductOutputDto;
import co.com.viveres.susy.microserviceproduct.dto.StockDto;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;
import co.com.viveres.susy.microserviceproduct.util.ResponseMessages;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private IContentRepository contentRepository;
	@Autowired
	private IMessageRepository messageRepository;

	@Override
	public ProductOutputDto create(ProductInputDto productDto) {
		this.validateProductAlreadyExist(productDto);

		ProductEntity productEntity = new ProductEntity();
		this.mapInProductDtoToEntity(productEntity, productDto);

		ProductEntity newProductEntity = this.productRepository.save(productEntity);
		return this.mapOutProductEntityToDto(newProductEntity);
	}

	private void validateProductAlreadyExist(ProductInputDto productDto) {

		ContentEntity contentEntity = this.contentRepository.findById(productDto.getContent().getId())
				.orElseThrow(() -> new GenericException(new MessageEntity()));

		Optional<ProductEntity> productEntity = this.productRepository.findByNameAndBrandAndContent(productDto.getName(),
				productDto.getBrand(), contentEntity);

		if (productEntity.isPresent())
			throw this.setGenericException(ResponseMessages.PRODUCT_ALREADY_EXISTS,
					productDto.getName()
					.concat(" ")
					.concat(productDto.getBrand())
					.concat(" de ")
					.concat(String.valueOf(contentEntity.getValue())
							.concat(" ")
							.concat(String.valueOf(contentEntity.getMeasureType().getName())))					
					);
					
	}

	private ProductEntity mapInProductDtoToEntity(ProductEntity productEntity, ProductInputDto productDto) {

		ContentEntity contentEntity = this.contentRepository.findById(productDto.getContent().getId())
				.orElseThrow(() -> this.setGenericException(ResponseMessages.CONTENT_DOES_NOT_EXIST,
						String.valueOf(productDto.getContent().getId())));

		productEntity.setContent(contentEntity);
		productEntity.setName(productDto.getName());
		productEntity.setBrand(productDto.getBrand());
		productEntity.setPrice(productDto.getPrice());
		productEntity.setCurrentNumItems(productDto.getCurrentNumItems());
		productEntity.setMinimunStock(productDto.getMinimunStock());
		productEntity.setDescription(productDto.getDescription());

		return productEntity;
	}

	private ProductOutputDto mapOutProductEntityToDto(ProductEntity productEntity) {

		ProductOutputDto productDto = new ProductOutputDto();
		productDto.setId(productEntity.getId());
		productDto.setName(productEntity.getName());
		productDto.setBrand(productEntity.getBrand());
		productDto.setPrice(productEntity.getPrice());
		productDto.setCurrentNumItems(productEntity.getCurrentNumItems());
		productDto.setMinimunStock(productEntity.getMinimunStock());
		productDto.setDescription(productEntity.getDescription());
		productDto.setContent(new ContentOutputDto());
		productDto.getContent().setId(productEntity.getContent().getId());
		productDto.getContent().setMeasure(productEntity.getContent().getMeasureType().getName());
		productDto.getContent().setValue(productEntity.getContent().getValue());

		return productDto;
	}

	@Override
	public List<ProductOutputDto> findAll() {			
		List<ProductEntity> productEntityList = this.productRepository.findAll();
		return this.mapOutListProductEntityToDto(productEntityList);
	}

	private List<ProductOutputDto> mapOutListProductEntityToDto(List<ProductEntity> productEntityList) {
		List<ProductOutputDto> productDtoList = new ArrayList<>();
		productEntityList.forEach(productEntity -> {
			ProductOutputDto productDto = this.mapOutProductEntityToDto(productEntity);
			productDtoList.add(productDto);
		});
		return productDtoList;
	}

	@Override
	public ProductOutputDto findById(Long id) {
		ProductEntity productEntity = this.productRepository.findById(id)
				.orElseThrow(() -> this.setGenericException(ResponseMessages.PRODUCT_DOES_NOT_EXIST,String.valueOf(id)));
		return this.mapOutProductEntityToDto(productEntity);
	}

	@Override
	public void update(Long id, ProductInputDto productDto) {

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
	
	private GenericException setGenericException(String responseMessage, String value) {
		
		MessageEntity message = this.messageRepository.findById(responseMessage)
				.orElseThrow(NoSuchElementException::new);
		message.setDescripction(String.format(message.getDescripction(), value));
				
		return new GenericException(message);		
	}

}
