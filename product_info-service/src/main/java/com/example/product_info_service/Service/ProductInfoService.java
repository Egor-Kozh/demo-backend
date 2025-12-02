package com.example.product_info_service.Service;

import com.example.product_info_service.Model.Dto.ProductInfoDto;
import com.example.product_info_service.Model.Entity.ProductInfoEntity;
import com.example.product_info_service.Model.Factory.ProductInfoDtoFactory;
import com.example.product_info_service.Model.Factory.ProductInfoEntityFactory;
import com.example.product_info_service.Repository.ProductInfoRepository;
import com.example.product_info_service.Service.Client.ProductServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductInfoService implements IProductInfoService {

    private final ProductInfoRepository productInfoRepository;

    private final ProductInfoDtoFactory productInfoDtoFactory;

    private final ProductInfoEntityFactory productInfoEntityFactory;

    private final ProductServiceClient productServiceClient;

    @Override
    public ResponseEntity<List<ProductInfoDto>> getAllProductInfo() throws RuntimeException {

        List<ProductInfoEntity> listProductInfo = productInfoRepository.findAll();

        return ResponseEntity.ok(listProductInfo.stream().map(productInfoDtoFactory::createProductInfoDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ProductInfoDto> getProductInfoById(UUID productInfoId) throws RuntimeException {

        ProductInfoEntity productInfoEntity = productInfoRepository.getProductInfoById(productInfoId);

        if (productInfoEntity == null) {
            throw new RuntimeException("Информация о товаре с id не существует!");
        }

        return ResponseEntity.ok(productInfoDtoFactory.createProductInfoDto(productInfoEntity));
    }

    @Override
    public ResponseEntity<List<ProductInfoDto>> getProductInfoByProductId(UUID productId) throws RuntimeException {

        List<ProductInfoEntity> listProductInfo = productInfoRepository.getProductInfoByProductId(productId);

        if (listProductInfo.isEmpty()) {
            throw new RuntimeException("Информации о товаре для товара с product_id не существует!");
        }

        return ResponseEntity.ok(listProductInfo.stream().map(productInfoDtoFactory::createProductInfoDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<String> createProductInfo(ProductInfoDto productInfoDto) throws RuntimeException {

        ProductInfoEntity createEntity = productInfoEntityFactory.createProductInfoEntity(productInfoDto);

        ProductInfoEntity entity = productInfoRepository.getProductInfoById(createEntity.getProductInfoID());

        if (entity != null) {
            throw new RuntimeException("Информации о товаре для товара с product_id не существует!");
        }

        ResponseEntity<Boolean> companyResponse = productServiceClient.validateProduct(createEntity.getProductId());

        if (!Boolean.TRUE.equals(companyResponse.getBody())) {
            throw new RuntimeException("Компания не найдена!");
        }

        return ResponseEntity.ok("Информация о товаре успешно создана!");
    }
}
