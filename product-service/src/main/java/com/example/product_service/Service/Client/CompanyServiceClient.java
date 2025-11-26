package com.example.product_service.Service.Client;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyServiceClient {

    private final WebClient webClient;

    public ResponseEntity<Boolean> validateCompany(UUID companyId) {
        return ResponseEntity.ok(webClient
                .get()
                .uri("company/check/{companyId}", companyId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .blockOptional()
                .orElse(false));
    }
}
