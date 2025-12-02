package com.example.product_info_service.Service;

import com.example.product_info_service.Model.Dto.ProductInfoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IProductInfoService {

    ResponseEntity<List<ProductInfoDto>> getAllProductInfo() throws RuntimeException;

    ResponseEntity<ProductInfoDto> getProductInfoById(UUID productInfoId) throws RuntimeException;

    ResponseEntity<List<ProductInfoDto>> getProductInfoByProductId(UUID productId) throws RuntimeException;

    ResponseEntity<String> createProductInfo(ProductInfoDto productInfoDto) throws RuntimeException;
}
