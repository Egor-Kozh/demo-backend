package com.example.type_product_service.Controller;

import com.example.type_product_service.Model.Dto.TypeProductDto;
import com.example.type_product_service.Service.TypeProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/typeProduct")
@AllArgsConstructor
public class TypeProductController {

    private final TypeProductService typeProductService;

    @GetMapping
    public ResponseEntity<?> getAllTypeProduct() {
        return typeProductService.getAllTypeProduct();
    }

    @GetMapping(path = "/id/{typeProductId}")
    public ResponseEntity<?> getTypeProductById(@PathVariable UUID typeProductId) {
        try {
            return typeProductService.getTypeProductById(typeProductId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/name/{typeProductName}")
    public ResponseEntity<?> getTypeProductByName(@PathVariable String typeProductName) {
        try {
            return typeProductService.getTypeProductByName(typeProductName);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTypeProduct(@RequestBody TypeProductDto typeProductDto) {
        try {
            return typeProductService.createTypeProduct(typeProductDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
