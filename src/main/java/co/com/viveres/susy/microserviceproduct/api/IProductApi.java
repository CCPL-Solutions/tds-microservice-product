package co.com.viveres.susy.microserviceproduct.api;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(tags = "Product Controller", description = "Product management")
public interface IProductApi {

	@ApiOperation(
		value = "createProduct", 
		notes = "This operation allows create a new product", 
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Create", responseHeaders = {
			@ResponseHeader(name = "location", description = "Link to the created product", response = String.class)
		}),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})
    @PostMapping(
        path = "", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto> create(
        @Valid @RequestBody ProductDto request);

	@ApiOperation(
		value = "findAllProducts", 
		notes = "This operation allows to list products",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = ProductDto[].class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
    @GetMapping(
        path = "", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<ProductDto>> findAll(
    		@RequestParam(value = "page", defaultValue = "0") int page, 
    		@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "name") String sort,
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "productBrand", required = false) String productBrand);

	@ApiOperation(
		value = "findProductById", 
		notes = "This operation allows to find a product by identifier.",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = ProductDto.class),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})	
    @GetMapping(
        path = "/{product-id}", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto> findById(
    		@PathVariable("product-id") Long id);

	@ApiOperation(
		value = "updateProduct", 
		notes = "This operation allows to update a product.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
    @PutMapping(
        path = "/{product-id}", 
        consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(
    		@PathVariable("product-id") Long id, 
    		@RequestBody ProductDto request);

	@ApiOperation(
		value = "deleteProduct", 
		notes = "This operation allows to delete a product.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
    @DeleteMapping(path = "/{product-id}")
    ResponseEntity<Void> delete(
    		@PathVariable("product-id") Long id);
    
	@ApiOperation(
		value = "stockManagementByProduct", 
		notes = "This operation allows to update the stock of the product by adding or removing units.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
    @PutMapping(
    	path = "/{product-id}/stock", 
    	consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> stockManagementByProduct(
    		@PathVariable("product-id") Long id, 
    		@RequestBody StockDto movement);

}
