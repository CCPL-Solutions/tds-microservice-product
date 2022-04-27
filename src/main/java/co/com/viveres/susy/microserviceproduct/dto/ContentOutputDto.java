package co.com.viveres.susy.microserviceproduct.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContentOutputDto implements Serializable{

    private Long id;
    private String measure;
    private Integer value;

}
