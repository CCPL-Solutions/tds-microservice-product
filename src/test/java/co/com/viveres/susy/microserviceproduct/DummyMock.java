package co.com.viveres.susy.microserviceproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.com.viveres.susy.microservicecommons.dto.BrandDto;
import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microservicecommons.dto.MeasureTypeDto;
import co.com.viveres.susy.microservicecommons.dto.ProductDto;
import co.com.viveres.susy.microservicecommons.dto.StockDto;
import co.com.viveres.susy.microservicecommons.entity.MessageEntity;
import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;

public class DummyMock {
	
	public static Optional<BrandEntity> brandEntity() {		
		BrandEntity brand = new BrandEntity();
		brand.setId(1L);
		brand.setName("Diana");
		return Optional.of(brand);
	}
	
	public static Optional<ContentEntity> contentEntity() {		
		ContentEntity content = new ContentEntity();
		content.setId(1L);
		content.setMeasureType(measureType());
		content.setValue(500);
		return Optional.of(content);
	}
	
	public static MeasureTypeEntity measureType() {
		MeasureTypeEntity measureType = new MeasureTypeEntity();
		measureType.setId(1L);
		measureType.setName("gramos");
		return measureType;
	}
	
	public static Optional<ProductEntity> productEntity(){
		ProductEntity productEntity =new ProductEntity();
		productEntity.setId(1L);
		productEntity.setContent(contentEntity().orElseThrow());
		productEntity.setName("Arroz");
		productEntity.setBrand(new BrandEntity());
		productEntity.getBrand().setId(1L);
		productEntity.getBrand().setName("Diana");
		productEntity.setPrice(2500d);
		productEntity.setCurrentNumItems(15);
		productEntity.setMinimunStock(5);
		productEntity.setDescription("Arroz Diana x 500 gramos");
		return Optional.of(productEntity);
	}
	
	public static ProductEntity productEntityTwo(){
		ProductEntity product = new ProductEntity();
		product.setName("Gaseosa");
		product.setBrand(new BrandEntity());
		product.getBrand().setId(1L);
		product.getBrand().setName("Postobon");
		product.setDescription("Gaseosa Postobon x 1 litro");
		product.setPrice(3600d);
		product.setCurrentNumItems(6);
		product.setMinimunStock(3);
		product.setContent(new ContentEntity());
		product.getContent().setId(2L);
		return product;
	}
	
	public static Optional<MessageEntity> message(String code, String description, String codeMessage) {
		MessageEntity message = new MessageEntity();
		message.setCode(code);
		message.setDescripction(description);
		message.setCodeMessage(codeMessage);
		return Optional.of(message);	
	}
	
	public static ContentDto contentInputDto() {
		ContentDto contentInputDto = new ContentDto();
		contentInputDto.setId(1L);
		return contentInputDto;
	}
	
	public static ProductDto productInputDto(){
		ProductDto product =new ProductDto();
		product.setName("Arroz");
		product.setBrand(new BrandDto());
		product.getBrand().setId(1L);
		product.getBrand().setName("Diana");
		product.setPrice(2500d);
		product.setCurrentNumItems(15);
		product.setMinimunStock(5);
		product.setDescription("Arroz Diana x 500 gramos");
		product.setContent(contentInputDto());
		return product;
	}
	
	public static ProductDto productInputDtoUpdate(){
		ProductDto product =new ProductDto();
		product.setName("Arroz");
		product.setBrand(new BrandDto());
		product.getBrand().setId(1L);
		product.getBrand().setName("Diana");
		product.setPrice(3200d);
		product.setCurrentNumItems(20);
		product.setMinimunStock(10);
		product.setDescription("Arroz Diana x 500 gramos");
		product.setContent(contentInputDto());
		return product;
	}
	
	public static ContentDto contentOutputDto() {
		ContentDto content = new ContentDto();
		content.setId(1L);
		content.setMeasure(new MeasureTypeDto());
		content.getMeasure().setId(1L);
		content.getMeasure().setName("gramos");
		content.setValue(500);
		return content;
	}
	
	public static ProductDto productOutputDto(){
		ProductDto product =new ProductDto();
		product.setId(1L);
		product.setName("Arroz");
		product.setBrand(new BrandDto());
		product.getBrand().setId(1L);
		product.getBrand().setName("Diana");
		product.setPrice(2500d);
		product.setCurrentNumItems(15);
		product.setMinimunStock(5);
		product.setDescription("Arroz Diana x 500 gramos");
		product.setContent(contentOutputDto());
		return product;
	}
	
	public static List<ProductEntity> productEntityList(){
		List<ProductEntity> productEntityList = new ArrayList<>();
		ProductEntity productEntity1 =new ProductEntity();
		productEntity1.setId(1L);
		productEntity1.setContent(contentEntity().orElseThrow());
		productEntity1.setName("Arroz");
		productEntity1.setBrand(new BrandEntity());
		productEntity1.getBrand().setId(1L);
		productEntity1.getBrand().setName("Diana");
		productEntity1.setPrice(2500d);
		productEntity1.setCurrentNumItems(15);
		productEntity1.setMinimunStock(5);
		productEntity1.setDescription("Arroz Diana x 500 gramos");
		
		ProductEntity productEntity2 =new ProductEntity();
		productEntity2.setId(2L);
		productEntity2.setContent(contentEntity().orElseThrow());
		productEntity2.setName("Lenteja");
		productEntity2.setBrand(new BrandEntity());
		productEntity2.getBrand().setId(2L);
		productEntity2.getBrand().setName("Diana");
		productEntity2.setPrice(3000d);
		productEntity2.setCurrentNumItems(15);
		productEntity2.setMinimunStock(5);
		productEntity2.setDescription("Lenteja Diana x 500 gramos");
		
		ProductEntity productEntity3 =new ProductEntity();
		productEntity3.setId(1L);
		productEntity3.setContent(contentEntity().orElseThrow());
		productEntity3.setName("Frijol");
		productEntity3.setBrand(new BrandEntity());
		productEntity3.getBrand().setId(3L);
		productEntity3.getBrand().setName("Diana");
		productEntity3.setPrice(5000d);
		productEntity3.setCurrentNumItems(15);
		productEntity3.setMinimunStock(5);
		productEntity3.setDescription("Frijol Diana x 500 gramos");
		
		productEntityList.add(productEntity1);
		productEntityList.add(productEntity2);
		productEntityList.add(productEntity3);
		
		return productEntityList;
	}
	
	public static List<ProductDto> productOutputDtoList(){
		List<ProductDto> productOutputDtoList = new ArrayList<>();
		ProductDto productOutputDto1 =new ProductDto();
		productOutputDto1.setId(1L);
		productOutputDto1.setContent(contentOutputDto());
		productOutputDto1.setName("Arroz");
		productOutputDto1.setBrand(new BrandDto());
		productOutputDto1.getBrand().setId(1L);
		productOutputDto1.getBrand().setName("Diana");
		productOutputDto1.setPrice(2500d);
		productOutputDto1.setCurrentNumItems(15);
		productOutputDto1.setMinimunStock(5);
		productOutputDto1.setDescription("Arroz Diana x 500 gramos");
		
		ProductDto productOutputDto2 =new ProductDto();
		productOutputDto2.setId(2L);
		productOutputDto2.setContent(contentOutputDto());
		productOutputDto2.setName("Lenteja");
		productOutputDto2.setBrand(new BrandDto());
		productOutputDto2.getBrand().setId(2L);
		productOutputDto2.getBrand().setName("Diana");
		productOutputDto2.setPrice(3000d);
		productOutputDto2.setCurrentNumItems(15);
		productOutputDto2.setMinimunStock(5);
		productOutputDto2.setDescription("Lenteja Diana x 500 gramos");
		
		ProductDto productOutputDto3 =new ProductDto();
		productOutputDto3.setId(1L);
		productOutputDto3.setContent(contentOutputDto());
		productOutputDto3.setName("Frijol");
		productOutputDto3.setBrand(new BrandDto());
		productOutputDto3.getBrand().setId(3L);
		productOutputDto3.getBrand().setName("Diana");
		productOutputDto3.setPrice(5000d);
		productOutputDto3.setCurrentNumItems(15);
		productOutputDto3.setMinimunStock(5);
		productOutputDto3.setDescription("Frijol Diana x 500 gramos");
		
		productOutputDtoList.add(productOutputDto1);
		productOutputDtoList.add(productOutputDto2);
		productOutputDtoList.add(productOutputDto3);
		
		return productOutputDtoList;
	}
	
	public static StockDto stockDto(String type) {
		StockDto stockDto = new StockDto();
		stockDto.setType(type);
		stockDto.setNumberItems(2);
		return stockDto;		
	}
	
	public static List<ContentEntity> contentEntityList() {
		List<ContentEntity> contentEntityList = new ArrayList<>();
		
		ContentEntity content = new ContentEntity();
		content.setId(1L);
		content.setMeasureType(measureType());
		content.setValue(500);
		
		ContentEntity content2 = new ContentEntity();
		content2.setId(2L);
		content2.setMeasureType(measureType());
		content2.setValue(750);
		
		ContentEntity content3 = new ContentEntity();
		content3.setId(3L);
		content3.setMeasureType(measureType());
		content.setValue(1000);
		
		contentEntityList.add(content);
		contentEntityList.add(content2);
		contentEntityList.add(content3);
		
		return contentEntityList;
	}
	
	public static List<ContentDto> contentOutputDtoList() {
		List<ContentDto> ContentOutputDtoList = new ArrayList<>();
		
		ContentDto content = new ContentDto();
		content.setId(1L);
		content.setMeasure(new MeasureTypeDto());
		content.getMeasure().setId(1L);
		content.getMeasure().setName("gramos");
		content.setValue(500);
		
		ContentDto content2 = new ContentDto();
		content2.setId(2L);
		content.setMeasure(new MeasureTypeDto());
		content.getMeasure().setId(1L);
		content.getMeasure().setName("gramos");
		content2.setValue(750);
		
		ContentDto content3 = new ContentDto();
		content3.setId(3L);
		content.setMeasure(new MeasureTypeDto());
		content.getMeasure().setId(1L);
		content.getMeasure().setName("gramos");
		content.setValue(1000);
		
		ContentOutputDtoList.add(content);
		ContentOutputDtoList.add(content2);
		ContentOutputDtoList.add(content3);
		
		return ContentOutputDtoList;
	}

}
