package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import co.com.viveres.susy.microservicecommons.exceptions.GenericException;

public interface IProductService {

	ProductDto create(ProductDto request);

	List<ProductDto> findAll();

	ProductDto findById(Long id);

	void update(Long id, ProductDto request);

	void stockManagementByProduct(Long productId, StockDto movement);
	
	GenericException setGenericException(String responseMessage, String value);

}
