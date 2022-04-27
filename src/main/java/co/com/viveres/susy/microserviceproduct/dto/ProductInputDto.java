package co.com.viveres.susy.microserviceproduct.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductInputDto extends BaseProductDto {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Valid
	private ContentInputDto content;

}
