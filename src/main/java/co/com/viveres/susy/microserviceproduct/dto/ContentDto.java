package co.com.viveres.susy.microserviceproduct.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Content")
public class ContentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@ApiModelProperty(notes = "Content Id", example = "1")
	private Long id;
	
	@ApiModelProperty(notes = "Measure", example = "gramos")
	private String measure;
	
	@ApiModelProperty(notes = "Value", example = "500")
	private Integer value;

}
