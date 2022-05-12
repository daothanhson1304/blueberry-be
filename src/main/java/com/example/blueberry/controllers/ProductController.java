package com.example.blueberry.controllers;

import com.example.blueberry.models.Product;
import com.example.blueberry.repository.ProductRepository;
import com.example.blueberry.security.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productRepository.findAll());
    }
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        product.setId(sequenceGeneratorService.getSequenceNumber(Product.SEQUENCE_NAME));
        System.out.println(product.getCategoryId());
        return productRepository.save(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") Integer id){
        productRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductbyId(@PathVariable("id") Integer id){
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceAccessException("Product not found"));;
        return ResponseEntity.ok(product);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable("id") Integer id,@RequestBody Product product) {
        Product currentProduct=productRepository.findById(id).orElseThrow(()->new ResourceAccessException("Product not found"));
        currentProduct.setCategoryId(product.getCategoryId());
        currentProduct.setDescription(product.getDescription());

        currentProduct.setPrice(product.getPrice());

        currentProduct.setImage(product.getImage());

        currentProduct.setQuantity(product.getQuantity());
        currentProduct.setSale(product.getSale());

        currentProduct.setStatus(product.getStatus());
        currentProduct.setTitle(product.getTitle());
        productRepository.save(currentProduct);
        return ResponseEntity.ok(product);
    }
}
