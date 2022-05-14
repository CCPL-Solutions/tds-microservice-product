package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microserviceproduct.dto.ProductDto;
import co.com.viveres.susy.microserviceproduct.dto.StockDto;

public interface IProductService {

	public ProductDto create(ProductDto request);

	public List<ProductDto> findAll();

	public ProductDto findById(Long id);

	public void update(Long id, ProductDto request);

	public void stockManagementByProduct(Long productId, StockDto movement);

}
