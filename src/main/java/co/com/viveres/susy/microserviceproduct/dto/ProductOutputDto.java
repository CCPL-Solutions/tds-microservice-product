package co.com.viveres.susy.microserviceproduct.dto;

import lombok.Data;

@Data
public class ProductOutputDto extends BaseProductDto {

    private Long id;
    private ContentOutputDto content;

}
