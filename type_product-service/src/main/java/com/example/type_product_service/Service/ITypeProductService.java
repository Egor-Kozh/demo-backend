package com.example.type_product_service.Service;

import com.example.type_product_service.Model.Dto.TypeProductDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ITypeProductService {

    ResponseEntity<?> getAllTypeProduct();

    ResponseEntity<?> getTypeProductById(UUID typeProductId);

    ResponseEntity<?> getTypeProductByName(String typeProductName);

    ResponseEntity<?> createTypeProduct(TypeProductDto typeProductDto);
}
