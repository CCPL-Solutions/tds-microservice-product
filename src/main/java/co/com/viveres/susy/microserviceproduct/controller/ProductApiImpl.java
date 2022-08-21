package co.com.viveres.susy.microserviceproduct.controller;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import co.com.viveres.susy.microserviceproduct.api.IProductApi;
import co.com.viveres.susy.microserviceproduct.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/products")
public class ProductApiImpl implements IProductApi {

    @Autowired
    private IProductService service;

    @Override
    public ResponseEntity<ProductDto> create(ProductDto request) {
        ProductDto response = service.create(request);
        return this.buildCreatResponse(response);
    }
    
	@Override
	public ResponseEntity<Page<ProductDto>> findAll(int page, int size, String sort, String productName, String productBrand) {
		Page<ProductDto> response = service.findAll(page, size, sort, productName, productBrand);
        return ResponseEntity.ok(response);
	}

    @Override
    public ResponseEntity<ProductDto> findById(Long id) {
        ProductDto response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> update(Long id, ProductDto request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }

	@Override
	public ResponseEntity<Void> stockManagementByProduct(Long id, StockDto movement) {
		this.service.stockManagementByProduct(id, movement);
		return ResponseEntity.ok().build();
	}
	
	private ResponseEntity<ProductDto> buildCreatResponse(
			ProductDto response) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{product-id}")
				.buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}

}
