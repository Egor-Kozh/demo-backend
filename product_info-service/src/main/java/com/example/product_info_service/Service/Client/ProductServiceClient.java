package com.example.product_info_service.Service.Client;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceClient {

    private final WebClient webClient;

    public ResponseEntity<Boolean> validateProduct(UUID productId) {
        return ResponseEntity.ok(webClient
                .get()
                .uri("product/check/{productId}", productId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .blockOptional()
                .orElse(false));
    }
}