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

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(tags = "Brand Controller", description = "Brand management")
public interface IBrandApi {

	@ApiOperation(
			value = "createBrand", 
			notes = "This operation allows create a new brand to brand", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Create", responseHeaders = {
				@ResponseHeader(name = "location", description = "Link to the created brand", response = String.class)
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
	ResponseEntity<BrandDto> create(@Valid @RequestBody BrandDto brand);

	@ApiOperation(
			value = "findAllBrands", 
			notes = "This operation allows to list brands",
			produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "OK", response = BrandDto[].class),
				@ApiResponse(code = 400, message = "Bad request"),
				@ApiResponse(code = 401, message = "Unauthorized"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not Found"),
				@ApiResponse(code = 500, message = "Internal server error")
		})
	    @GetMapping(
	        path = "", 
	        produces = MediaType.APPLICATION_JSON_VALUE)	
	ResponseEntity<List<BrandDto>> findAllBrands();

	@ApiOperation(
			value = "findBrandById", 
			notes = "This operation allows to find a brand by identifier.",
			produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = BrandDto.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
		})	
	    @GetMapping(
	        path = "/{brand-id}", 
	        produces = MediaType.APPLICATION_JSON_VALUE)	
	ResponseEntity<BrandDto> findById(@PathVariable(name = "brand-id") Long brandId);

	@ApiOperation(
			value = "updateBrand", 
			notes = "This operation allows to update a brand.")
		@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
		})		
	    @PutMapping(
	        path = "/{brand-id}", 
	        consumes = MediaType.APPLICATION_JSON_VALUE)	
	ResponseEntity<Void> update(@PathVariable(name = "brand-id") Long brandId, @RequestBody BrandDto brand);

	@ApiOperation(
			value = "deleteBrand", 
			notes = "This operation allows to delete a brand.")
		@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
		})		
	    @DeleteMapping(path = "/{brand-id}")	
	ResponseEntity<Void> delete(@PathVariable(name = "brand-id") Long brandId);
}
