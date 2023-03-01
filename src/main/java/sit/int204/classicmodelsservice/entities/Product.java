package sit.int204.classicmodelsservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productScale;
    private String productVendor;
    private String productLine;
    @Column(name = "productDescription")
    private String description;
    private Integer quantityInStock;
    private Double buyPrice;
    @Column(name = "MSRP")
    private Double price;

}
