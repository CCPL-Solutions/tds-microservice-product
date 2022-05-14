package co.com.viveres.susy.microserviceproduct.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BRAND")
public class BrandEntity {

	@Id
	@SequenceGenerator(name = "SEQ_BRAND_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRAND_ID")
    @Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;
}
