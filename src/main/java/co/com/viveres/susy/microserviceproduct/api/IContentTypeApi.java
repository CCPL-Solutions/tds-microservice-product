package co.com.viveres.susy.microserviceproduct.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import co.com.viveres.susy.microserviceproduct.dto.ContentDto;

public interface IContentTypeApi {

	@GetMapping(path = "")
	public ResponseEntity<List<ContentDto>> findAllContent();

}
