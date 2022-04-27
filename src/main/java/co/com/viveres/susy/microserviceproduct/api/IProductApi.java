package co.com.viveres.susy.microserviceproduct.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.viveres.susy.microserviceproduct.dto.ProductInputDto;
import co.com.viveres.susy.microserviceproduct.dto.ProductOutputDto;
import co.com.viveres.susy.microserviceproduct.dto.StockDto;

public interface IProductApi {

    @PostMapping(
        path = "", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOutputDto> create(
        @Valid @RequestBody ProductInputDto request);

    @GetMapping(
        path = "", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductOutputDto>> findAll();

    @GetMapping(
        path = "/{product-id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductOutputDto> findById(
    		@PathVariable("product-id") Long id);

    @PutMapping(
        path = "/{product-id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(
    		@PathVariable("product-id") Long id, 
    		@RequestBody ProductInputDto request);

    @DeleteMapping(path = "/{product-id}")
    public ResponseEntity<Void> delete(
    		@PathVariable("product-id") Long id);
    
    @PutMapping(
    	path = "/{product-id}/stock", 
    	consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> stockManagementByProduct(
    		@PathVariable("product-id") Long id, 
    		@RequestBody StockDto movement);

}
