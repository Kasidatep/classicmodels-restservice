package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(int page, int size, String sortBy){
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductBetweenPrice(Double low, Double high) {
        return productRepository.findProductByPriceBetweenOrderByPriceAsc(low, high);
    }

    public List<Product> getProductByProductLine(String productLine, String sortBy) {
        Sort sort = Sort.by(sortBy);
        return productRepository.findProductByProductLineContainingIgnoreCase(productLine, sort);
    }

    public Product updateProduct(String productCode, Product product) {
        Product oldProduct = productRepository.findById(productCode).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product Code: " + productCode + " DOES NOT EXIST !!!"));
        oldProduct.setProductName(product.getProductName());
        oldProduct.setProductLine(product.getProductLine());
        oldProduct.setProductScale(product.getProductScale());
        oldProduct.setProductVendor(product.getProductVendor());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setBuyPrice(product.getBuyPrice());
        oldProduct.setQuantityInStock(product.getQuantityInStock());
        productRepository.saveAndFlush(oldProduct);
        return oldProduct;
    }

    public Product addProduct(Product product) {
        if(!productRepository.existsById(product.getProductCode())) {
            productRepository.saveAndFlush(product);
        }
        return productRepository.findById(product.getProductCode()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product Code: " + product.getProductCode() + " DOES NOT EXIST !!!"));
    }
}
