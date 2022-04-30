package co.com.viveres.susy.microserviceproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microserviceproduct.entity.BrandEntity;

@Repository
public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

}
