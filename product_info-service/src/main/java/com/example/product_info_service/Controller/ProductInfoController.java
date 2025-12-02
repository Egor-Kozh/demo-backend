package com.example.product_info_service.Controller;

import com.example.product_info_service.Model.Dto.ProductInfoDto;
import com.example.product_info_service.Service.ProductInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "productInfo")
@AllArgsConstructor
public class ProductInfoController {

    private final ProductInfoService productInfoService;

    @GetMapping
    public ResponseEntity<?> getAllProductInfo() {
        return null;
    }

    @GetMapping(path = "/id/{productInfoId}")
    public ResponseEntity<?> getProductInfoById(UUID productInfoId) {
        return null;
    }

    @GetMapping(path = "/product_id/{productId}")
    public ResponseEntity<?> getProductInfoByProductId(UUID productId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createProductInfo(ProductInfoDto productInfoDto) {
        return null;
    }
}
