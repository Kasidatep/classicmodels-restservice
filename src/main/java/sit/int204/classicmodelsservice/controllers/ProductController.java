package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/pages")
    public Page<Product> getProductsPages(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "productCode") String sortBy
    ){
        return productService.getProducts(page,size,sortBy);
    }

    @GetMapping("")
    public List<Product> getProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "productCode") String sortBy
    ){
        return getProductsPages(page, size, sortBy).getContent();
    }
    @GetMapping("/{low}/{high}")
    public List<Product> getProductBetweenPrice(
            @PathVariable Double low,
            @PathVariable Double high
    ){
        return productService.getProductBetweenPrice(low,high);
    }

    @GetMapping("/{productLine}")
    public List<Product> getProductByProductLine(@PathVariable String productLine){
        return productService.getProductByProductLine(productLine);
    }

    @PutMapping("/{productCode}")
    public Product updateProduct(@PathVariable String productCode, @RequestBody Product product){
        return productService.updateProduct(productCode, product);
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

}
