package co.com.viveres.susy.microserviceproduct.service.mapper.impl;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;
import co.com.viveres.susy.microserviceproduct.service.mapper.IMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements IMapper {

    //MeasureTypeMapper
    @Override
    public MeasureTypeEntity mapInMeasureTypeDtoToEntity(MeasureTypeDto measureDto) {
        return MeasureTypeEntity.builder().name(measureDto.getName()).build();
    }

    @Override
    public MeasureTypeDto mapOutMeasureTypeEntityToDto(MeasureTypeEntity measureEntity) {
        MeasureTypeDto measureDto = new MeasureTypeDto();
        measureDto.setId(measureEntity.getId());
        measureDto.setName(measureEntity.getName());
        return measureDto;
    }

    //ContentMapper

    @Override
    public ContentEntity mapInContentDtoToEntity(ContentDto contentDto, MeasureTypeEntity measureTypeEntity) {
        ContentEntity contentEntity= new ContentEntity();
        contentEntity.setValue(contentDto.getValue());
        contentEntity.setMeasureType(measureTypeEntity);
        return contentEntity;
    }

    @Override
    public ContentDto mapOutContentEntityToDto(ContentEntity contentEntity) {
        ContentDto contentDto = new ContentDto();
        contentDto.setId(contentEntity.getId());
        contentDto.setMeasure(new MeasureTypeDto());
        contentDto.getMeasure().setId(contentEntity.getMeasureType().getId());
        contentDto.getMeasure().setName(contentEntity.getMeasureType().getName());
        contentDto.setValue(contentEntity.getValue());
        return contentDto;
    }

    //BrandMapper

    @Override
    public BrandEntity mapInBrandDtoToEntity(BrandDto brand) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName(brand.getName());
        return brandEntity;
    }

    @Override
    public BrandDto mapOutBrantEntityToDto(BrandEntity brandEntity) {
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brandEntity.getId());
        brandDto.setName(brandEntity.getName());
        return brandDto;
    }

    //ProductMapper

    @Override
    public ProductEntity mapInProductDtoToEntity(ProductEntity productEntity, ProductDto productDto,
                                                 ContentEntity contentEntity, BrandEntity brandEntity) {
        productEntity.setContent(contentEntity);
        productEntity.setName(productDto.getName());
        productEntity.setBrand(brandEntity);
        productEntity.setPrice(productDto.getPrice());
        productEntity.setCurrentNumItems(productDto.getCurrentNumItems());
        productEntity.setMinimunStock(productDto.getMinimunStock());
        productEntity.setDescription(productDto.getDescription());
        return productEntity;
    }

    @Override
    public ProductDto mapOutProductEntityToDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setBrand(new BrandDto());
        productDto.getBrand().setId(productEntity.getBrand().getId());
        productDto.getBrand().setName(productEntity.getBrand().getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setCurrentNumItems(productEntity.getCurrentNumItems());
        productDto.setMinimunStock(productEntity.getMinimunStock());
        productDto.setDescription(productEntity.getDescription());
        productDto.setContent(new ContentDto());
        productDto.getContent().setId(productEntity.getContent().getId());
        productDto.getContent().setMeasure(new MeasureTypeDto());
        productDto.getContent().getMeasure().setId(productEntity.getContent().getMeasureType().getId());
        productDto.getContent().getMeasure().setName(productEntity.getContent().getMeasureType().getName());
        productDto.getContent().setValue(productEntity.getContent().getValue());
        return productDto;
    }
}
