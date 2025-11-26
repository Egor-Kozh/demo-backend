package com.example.product_service.Service;

import com.example.product_service.Model.Dto.ProductDto;
import com.example.product_service.Model.Entity.ProductEntity;
import com.example.product_service.Model.Factory.ProductDtoFactory;
import com.example.product_service.Model.Factory.ProductEntityFactory;
import com.example.product_service.Repository.ProductRepository;
import com.example.product_service.Service.Client.CompanyServiceClient;
import com.example.product_service.Service.Client.TypeProductServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    private final ProductDtoFactory productDtoFactory;

    private final ProductEntityFactory productEntityFactory;

    private final CompanyServiceClient companyServiceClient;

    private final TypeProductServiceClient typeProductServiceClient;

    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() throws RuntimeException {
        List<ProductEntity> listProduct = productRepository.findAll();

        return ResponseEntity.ok().body(listProduct.stream().map(productDtoFactory::createProductDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(UUID productId) throws RuntimeException {
        ProductEntity product = productRepository.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Продукта с таким id не существует!");
        }

        return ResponseEntity.ok().body(productDtoFactory.createProductDto(product));
    }

    @Override
    public ResponseEntity<ProductDto> getProductsByName(String productName) throws RuntimeException {
        ProductEntity product = productRepository.getProductByName(productName);

        if (product == null) {
            throw new RuntimeException("Продукта с таким name не существует!");
        }

        return ResponseEntity.ok().body(productDtoFactory.createProductDto(product));
    }

    @Override
    public ResponseEntity<String> createProduct(ProductDto product) throws RuntimeException {
        ProductEntity entity = productEntityFactory.createProductEntity(product);

        ProductEntity entityName = productRepository.getProductByName(entity.getProductName());
        if (entityName != null) {
            throw new RuntimeException("Продукт с таким name уже существует!");
        }

        ResponseEntity companyResponse = companyServiceClient.validateCompany(entity.getCompanyId());
        if (companyResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new RuntimeException("Компания не найдена!");
        }

        ResponseEntity typeProductResponse = typeProductServiceClient.validateTypeProduct(entity.getTypeProductId());
        if (typeProductResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new RuntimeException("Тип продукта не найден!");
        }

        productRepository.saveAndFlush(entity);

        return ResponseEntity.ok().body("Продукт успешно создан!");
    }
}
