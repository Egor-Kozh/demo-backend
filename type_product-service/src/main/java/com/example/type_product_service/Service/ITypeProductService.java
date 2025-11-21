package com.example.type_product_service.Service;

import com.example.type_product_service.Model.Dto.TypeProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ITypeProductService {

    ResponseEntity<List<TypeProductDto>> getAllTypeProduct();

    ResponseEntity<TypeProductDto> getTypeProductById(UUID typeProductId);

    ResponseEntity<TypeProductDto> getTypeProductByName(String typeProductName);

    ResponseEntity<String> createTypeProduct(TypeProductDto typeProductDto);
}
