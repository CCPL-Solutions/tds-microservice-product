package co.com.viveres.susy.microserviceproduct.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONTENT")
public class ContentEntity{

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_MEASURE_TYPE_FK", unique = false, nullable = false)
    private MeasureTypeEntity measureType;
    
    @Column(name = "VALUE", unique = false, nullable = false)
    private Integer value;

}
