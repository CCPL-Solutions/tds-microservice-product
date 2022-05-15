package co.com.viveres.susy.microserviceproduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;

@Repository
public interface IMeasureTypeRepository extends JpaRepository<MeasureTypeEntity, Long>{
  
	Optional<MeasureTypeEntity> findByName(String name);
}
