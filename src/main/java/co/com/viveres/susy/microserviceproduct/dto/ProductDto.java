package co.com.viveres.susy.microserviceproduct.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Product")
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Product Id", example = "1",readOnly = true, required = false)
	private Long id;

	@NotNull
	@NotBlank
	@ApiModelProperty(notes = "Product name", example = "Arróz", required = true)
	private String name;

	@Valid
	@NotNull
	@ApiModelProperty(notes = "Brand", example = "Diana", required = true)
	private BrandDto brand;

	@NotNull
	@Valid
	@ApiModelProperty(notes = "Content", required = true)
	private ContentDto content;

	@NotNull
	@ApiModelProperty(notes = "Price", example = "3500", required = true)
	private Double price;

	@NotNull
	@Min(value = 0)
	@ApiModelProperty(notes = "Current Numbre Items", example = "10", required = true)
	private Integer currentNumItems;

	@NotNull
	@Min(value = 0)
	@ApiModelProperty(notes = "Minimun stocks", example = "10", required = true)
	private Integer minimunStock;

	@NotNull
	@NotBlank
	@ApiModelProperty(notes = "Description", example = "Arróz Diana x 500 gramos", required = true)
	private String description;

}
