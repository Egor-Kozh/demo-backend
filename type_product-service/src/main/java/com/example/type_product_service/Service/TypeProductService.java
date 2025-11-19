package com.example.type_product_service.Service;

import com.example.type_product_service.Model.Dto.TypeProductDto;
import com.example.type_product_service.Model.Entity.TypeProductEntity;
import com.example.type_product_service.Model.Factory.TypeProductDtoFactory;
import com.example.type_product_service.Model.Factory.TypeProductEntityFactory;
import com.example.type_product_service.Repository.TypeProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeProductService implements ITypeProductService {

    private final TypeProductRepository typeProductRepository;

    private final TypeProductDtoFactory typeProductDtoFactory;

    private final TypeProductEntityFactory typeProductEntityFactory;

    @Override
    public ResponseEntity<?> getAllTypeProduct() {
        List<TypeProductEntity> typeProducts = typeProductRepository.findAll();

        return ResponseEntity.ok(typeProducts.stream().map(typeProductDtoFactory::createTypeProductDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> getTypeProductById(UUID typeProductId) {
        TypeProductEntity typeProduct = typeProductRepository.findTypeProductById(typeProductId);

        return ResponseEntity.ok(typeProductDtoFactory.createTypeProductDto(typeProduct));
    }

    @Override
    public ResponseEntity<?> getTypeProductByName(String typeProductName) {
        TypeProductEntity typeProduct = typeProductRepository.findTypeProductByName(typeProductName);

        return ResponseEntity.ok(typeProductDtoFactory.createTypeProductDto(typeProduct));
    }

    @Override
    public ResponseEntity<?> createTypeProduct(TypeProductDto typeProductDto) {
        TypeProductEntity typeProduct = typeProductEntityFactory.createTypeProductEntity(typeProductDto);

        typeProductRepository.saveAndFlush(typeProduct);

        return ResponseEntity.ok("Type product created!");
    }
}
