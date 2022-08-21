package co.com.viveres.susy.microserviceproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "MEASURE_TYPE")
public class MeasureTypeEntity{

	@Id
	@SequenceGenerator(name = "SEQ_MEASURE_TYPE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEASURE_TYPE_ID")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
	
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    
}
