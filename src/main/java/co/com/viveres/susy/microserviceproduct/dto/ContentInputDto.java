package co.com.viveres.susy.microserviceproduct.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContentInputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

}
