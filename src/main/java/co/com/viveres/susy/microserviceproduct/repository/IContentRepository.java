package co.com.viveres.susy.microserviceproduct.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microserviceproduct.entity.ContentEntity;
import co.com.viveres.susy.microserviceproduct.entity.MeasureTypeEntity;

@Repository
public interface IContentRepository extends JpaRepository<ContentEntity, Long>{
  
	Optional<ContentEntity> findByMeasureTypeAndValue(
			MeasureTypeEntity measureType, Double value);
}
