package co.com.viveres.susy.microserviceproduct.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class ProductEntity{
    
	@Id
	@SequenceGenerator(name = "SEQ_PRODUCT_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT_ID")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CONTENT_FK", nullable = false)
    private ContentEntity content;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "CURRENT_NUM_ITEMS", nullable = false)
    private Integer currentNumItems;

    @Column(name = "MINIMUM_STOCK", nullable = false)
    private Integer minimunStock;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

}
