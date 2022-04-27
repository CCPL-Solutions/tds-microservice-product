package co.com.viveres.susy.microserviceproduct.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BaseProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String brand;

	@NotNull
	private Double price;

	@NotNull
	@Min(value = 0)
	private Integer currentNumItems;

	@NotNull
	@Min(value = 0)
	private Integer minimunStock;

	@NotNull
	@NotBlank
	private String description;

}
