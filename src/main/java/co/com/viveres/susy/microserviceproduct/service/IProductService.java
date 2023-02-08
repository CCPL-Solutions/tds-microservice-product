package co.com.viveres.susy.microserviceproduct.service;

import org.springframework.data.domain.Page;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;

public interface IProductService {

	ProductDto create(ProductDto request);

	Page<ProductDto> findAll(int page, int size, String sort, String productName, String productBran);

	ProductDto findById(Long id);

	void update(Long id, ProductDto request);

	void stockManagementByProduct(Long productId, StockDto movement);

	void delete(Long id);

}
