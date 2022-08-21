package co.com.viveres.susy.microserviceproduct.service.mapper;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;

public interface IMapper {

    //MeasureTypeMapper
    MeasureTypeEntity mapInMeasureTypeDtoToEntity(MeasureTypeDto measureDto);

    MeasureTypeDto mapOutMeasureTypeEntityToDto(MeasureTypeEntity measureEntity);

    //ContentMapper

    ContentEntity mapInContentDtoToEntity(ContentDto contentDto, MeasureTypeEntity measureTypeEntity);

    ContentDto mapOutContentEntityToDto(ContentEntity contentEntity);

    //BrandMapper

    BrandEntity mapInBrandDtoToEntity(BrandDto brand);

    BrandDto mapOutBrantEntityToDto(BrandEntity brandEntity);

    //ProductMapper

    ProductEntity mapInProductDtoToEntity(ProductEntity productEntity, ProductDto productDto,
                                          ContentEntity contentEntity, BrandEntity brandEntity);

    ProductDto mapOutProductEntityToDto(ProductEntity productEntity);

}
