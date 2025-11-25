package com.example.product_service.Service;

import com.example.product_service.Model.Dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ResponseEntity<List<ProductDto>> getAllProducts() throws RuntimeException;

    ResponseEntity<ProductDto> getProductById(UUID productId) throws RuntimeException;

    ResponseEntity<ProductDto> getProductsByName(String productName) throws RuntimeException;

    ResponseEntity<String> createProduct(ProductDto product) throws RuntimeException;
}
