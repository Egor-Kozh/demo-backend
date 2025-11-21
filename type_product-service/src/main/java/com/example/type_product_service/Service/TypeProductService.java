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
    public ResponseEntity<List<TypeProductDto>> getAllTypeProduct() {
        List<TypeProductEntity> typeProducts = typeProductRepository.findAll();

        return ResponseEntity.ok(typeProducts.stream().map(typeProductDtoFactory::createTypeProductDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<TypeProductDto> getTypeProductById(UUID typeProductId) throws RuntimeException {
        TypeProductEntity typeProduct = typeProductRepository.findTypeProductById(typeProductId);

        if (typeProduct == null) {
            throw new RuntimeException("Тип товара с таким id не существует!");
        }

        return ResponseEntity.ok(typeProductDtoFactory.createTypeProductDto(typeProduct));
    }

    @Override
    public ResponseEntity<TypeProductDto> getTypeProductByName(String typeProductName) throws RuntimeException {
        TypeProductEntity typeProduct = typeProductRepository.findTypeProductByName(typeProductName);

        if (typeProduct == null) {
            throw new RuntimeException("Тип товара с таким name не существует!");
        }

        return ResponseEntity.ok(typeProductDtoFactory.createTypeProductDto(typeProduct));
    }

    @Override
    public ResponseEntity<String> createTypeProduct(TypeProductDto typeProductDto) throws RuntimeException {
        TypeProductEntity typeProduct = typeProductEntityFactory.createTypeProductEntity(typeProductDto);

        TypeProductEntity typeProductByName = typeProductRepository.findTypeProductByName(typeProduct.getTypeProductName());

        if (typeProductByName != null) {
            throw new RuntimeException("Тип товара с таким name уже существует!");
        }

        typeProductRepository.saveAndFlush(typeProduct);

        return ResponseEntity.ok("Type product created!");
    }
}
