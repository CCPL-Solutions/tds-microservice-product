package co.com.viveres.susy.microserviceproduct.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(tags = "Content type Controller", description = "Content type management")
public interface IContentTypeApi {
	
	@ApiOperation(
		value = "createContent", 
		notes = "This operation allows create a new content", 
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Create", responseHeaders = {
			@ResponseHeader(name = "location", description = "Link to the created content", response = String.class)
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
	ResponseEntity<ContentDto> create(@Valid @RequestBody ContentDto brand);	

	@ApiOperation(
		value = "findAllContentType", 
		notes = "This operation allows to list contents",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = ContentDto[].class),
		@ApiResponse(code = 400, message = "Bad request"),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal server error")
	})
	@GetMapping(
		path = "", 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ContentDto>> findAllContent();

}
