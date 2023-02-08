package co.com.viveres.susy.microserviceproduct.service.impl;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import co.com.viveres.susy.microservicecommons.exception.BusinessException;
import co.com.viveres.susy.microservicecommons.exception.NotFoundException;
import co.com.viveres.susy.microservicecommons.util.ResponseMessages;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.repository.IProductRepository;
import co.com.viveres.susy.microserviceproduct.service.IBrandService;
import co.com.viveres.susy.microserviceproduct.service.IContentService;
import co.com.viveres.susy.microserviceproduct.service.IProductService;
import co.com.viveres.susy.microserviceproduct.service.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private IContentService contentService;
	@Autowired
	private IBrandService brandService;

	@Autowired
	private IMapper mapper;

	@Override
	public ProductDto create(ProductDto productDto) {
		ContentEntity contentEntity = this.contentService.findContentEntityById(productDto.getContent().getId());
		BrandEntity brandEntity = this.brandService.findBrandEntityById(productDto.getBrand().getId());
		ProductEntity productEntity = new ProductEntity();

		this.validateProductAlreadyExist(productDto, contentEntity, brandEntity);
		this.mapper.mapInProductDtoToEntity(productEntity, productDto, contentEntity, brandEntity);

		return this.mapper.mapOutProductEntityToDto(this.persist(productEntity));
	}

	private void validateProductAlreadyExist(ProductDto productDto, ContentEntity contentEntity,
											 BrandEntity brandEntity) {

		Optional<ProductEntity> productEntity = this.productRepository.findByNameAndBrandAndContent(
				productDto.getName(),
				brandEntity, contentEntity);

		if (productEntity.isPresent())
			throw new BusinessException(ResponseMessages.PRODUCT_ALREADY_EXISTS);
					
	}

	private ProductEntity persist(ProductEntity productEntity) {
		return this.productRepository.save(productEntity);
	}

	@Override
	public Page<ProductDto> findAll(int page, int size, String sort, String productName, String productBran) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		Page<ProductEntity> productEntityPage = null;
		
		if (productName != null) {
			productEntityPage = this.productRepository.findByNameContainingAndIsActive(productName, pageable, Boolean.TRUE);
		}else if(productBran != null) {
			productEntityPage = this.productRepository.findByBrandNameContainingAndIsActive(productBran, pageable, Boolean.TRUE);
		}
		else {
			productEntityPage = this.productRepository.findAllByIsActive(pageable, Boolean.TRUE);
		}
				
		return this.mapOutListProductEntityToDto(productEntityPage);
	}

	private Page<ProductDto> mapOutListProductEntityToDto(Page<ProductEntity> productEntityPage) {					
		return productEntityPage.map(this.mapper::mapOutProductEntityToDto);
	}

	@Override
	public ProductDto findById(Long id) {
		ProductEntity productEntity = this.findProductById(id);
		return this.mapper.mapOutProductEntityToDto(productEntity);
	}

	private ProductEntity findProductById(Long id) {
		return this.productRepository.findById(id).orElseThrow(
				() -> new NotFoundException(ResponseMessages.PRODUCT_DOES_NOT_EXIST));
	}

	@Override
	public void update(Long id, ProductDto productDto) {

		ProductEntity productEntity = this.findProductById(id);
		ContentEntity contentEntity = this.contentService.findContentEntityById(productDto.getContent().getId());
		BrandEntity brandEntity = this.brandService.findBrandEntityById(productDto.getBrand().getId());

		this.mapper.mapInProductDtoToEntity(productEntity, productDto, contentEntity, brandEntity);
		this.productRepository.save(productEntity);

	}

	@Override
	public void stockManagementByProduct(Long productId, StockDto movement) {

		ProductEntity productEntity = this.findProductById(productId);

		if (movement.getType().equals("remove")) {
			productEntity.setCurrentNumItems(productEntity.getCurrentNumItems() - movement.getNumberItems());
		} else {
			productEntity.setCurrentNumItems(productEntity.getCurrentNumItems() + movement.getNumberItems());
		}

		this.persist(productEntity);

	}

	@Override
	public void delete(Long id) {
		ProductEntity productEntity = this.findProductById(id);
		productEntity.setIsActive(Boolean.FALSE);
		this.persist(productEntity);
	}

}
