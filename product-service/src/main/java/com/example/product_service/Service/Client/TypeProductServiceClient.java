package com.example.product_service.Service.Client;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TypeProductServiceClient {

    private final WebClient webClient;

    public ResponseEntity<Boolean> validateTypeProduct(UUID typeProductId) {
        return ResponseEntity.ok(webClient
                .get()
                .uri("type_product/check/{typeProductId}", typeProductId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .blockOptional()
                .orElse(false));
    }
}
