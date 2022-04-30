package co.com.viveres.susy.microserviceproduct.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.viveres.susy.microserviceproduct.DummyMock;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;

@DataJpaTest
class ProductRepositoryTest {

	@Autowired
	private IProductRepository repository;

	@Test
	void fingById() {
		Optional<ProductEntity> product = this.repository.findById(1L);
		assertTrue(product.isPresent());
		assertEquals("Arroz", product.orElseThrow().getName());
	}

	@Test
	void fingAll() {
		List<ProductEntity> products = this.repository.findAll();
		assertFalse(products.isEmpty());
		assertEquals(4, products.size());
	}

	@Test
	void save() {
		ProductEntity product = DummyMock.productEntityTwo();
		ProductEntity productSave = this.repository.save(product);
		
		assertNotNull(productSave);
		assertEquals(product.getName(), productSave.getName());
		assertEquals(product.getBrand(), productSave.getBrand());
	}
	
	@Test
	void update() {
		Optional<ProductEntity> product = this.repository.findById(1L);
		assertTrue(product.isPresent());
		assertEquals("Arroz", product.orElseThrow().getName());
		
		ProductEntity productEntity = product.orElseThrow();
		productEntity.setName("Otro");
		
		ProductEntity productUpdate = this.repository.save(productEntity);

		assertNotNull(productUpdate);
		assertEquals("Otro", productUpdate.getName());
		
	}

}
