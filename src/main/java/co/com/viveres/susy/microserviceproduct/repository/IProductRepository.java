package co.com.viveres.susy.microserviceproduct.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;
import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.ProductEntity;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

	Optional<ProductEntity> findByNameAndBrandAndContent(String name, 
			BrandEntity brand, ContentEntity content);
	
	Page<ProductEntity> findAll(Pageable pageable);
	
	Page<ProductEntity> findByNameContaining(String name, Pageable pageable);
	
	@Query(value = "select * from product p inner join brand b on p.id_brand_fk = b.id where b.name = ?1", nativeQuery = true)
	Page<ProductEntity> findByBrandNameContaining(String brand, Pageable pageable);

}
