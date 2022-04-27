package co.com.viveres.susy.microserviceproduct.service;

import java.util.List;

import co.com.viveres.susy.microserviceproduct.dto.ProductInputDto;
import co.com.viveres.susy.microserviceproduct.dto.ProductOutputDto;
import co.com.viveres.susy.microserviceproduct.dto.StockDto;

public interface IProductService {

	public ProductOutputDto create(ProductInputDto request);

	public List<ProductOutputDto> findAll();

	public ProductOutputDto findById(Long id);

	public void update(Long id, ProductInputDto request);

	public void stockManagementByProduct(Long productId, StockDto movement);

}
