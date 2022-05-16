package co.com.viveres.susy.microserviceproduct.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(tags = "Measure Type Controller", description = "Measure Type management")
public interface IMeasureTypeApi {

	@ApiOperation(
		value = "createMeasureType", 
		notes = "This operation allows create a new measure type", 
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Create", responseHeaders = {
			@ResponseHeader(name = "location", description = "Link to the created measure type", response = String.class)
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
	ResponseEntity<MeasureTypeDto> create(@Valid @RequestBody MeasureTypeDto measureDto);

	@ApiOperation(
			value = "findAllMeasureType", 
			notes = "This operation allows to list measure type",
			produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "OK", response = MeasureTypeDto[].class),
				@ApiResponse(code = 400, message = "Bad request"),
				@ApiResponse(code = 401, message = "Unauthorized"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not Found"),
				@ApiResponse(code = 500, message = "Internal server error")
		})
	    @GetMapping(
	        path = "", 
	        produces = MediaType.APPLICATION_JSON_VALUE)	
	ResponseEntity<List<MeasureTypeDto>> findAll();

	@ApiOperation(
			value = "findMeasureTypeById", 
			notes = "This operation allows to find a measure type by identifier.",
			produces = MediaType.APPLICATION_JSON_VALUE)
		@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = MeasureTypeDto.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal server error")
		})	
	    @GetMapping(
	        path = "/{measure-type-id}", 
	        produces = MediaType.APPLICATION_JSON_VALUE)	
	ResponseEntity<MeasureTypeDto> findById(@PathVariable(name = "measure-type-id") Long measureId);
	
	@ApiOperation(
		value = "updateMeasureType", 
		notes = "This operation allows to update a measure type.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})		
	@PutMapping(
		path = "/{measure-type-id}",  
	    consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(
	    @PathVariable("measure-type-id") Long id, 
	    @RequestBody MeasureTypeDto measureDto);	

}
