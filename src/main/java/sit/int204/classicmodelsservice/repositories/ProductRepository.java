package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findProductByPriceBetweenOrderByPriceAsc(Double low, Double high);
    List<Product> findProductByProductLineContainingIgnoreCase(String productLine);
}
