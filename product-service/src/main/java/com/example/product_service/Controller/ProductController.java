package com.example.product_service.Controller;

import com.example.product_service.Model.Dto.ProductDto;
import com.example.product_service.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            return productService.getAllProducts();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/id/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable UUID productId) {
        try {
            return productService.getProductById(productId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/name/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName) {
        try {
            return productService.getProductsByName(productName);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/check/{productId}")
    public ResponseEntity<?> validateProduct(@PathVariable UUID productId) {
        return productService.validateProduct(productId);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        try {
            return productService.createProduct(productDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
