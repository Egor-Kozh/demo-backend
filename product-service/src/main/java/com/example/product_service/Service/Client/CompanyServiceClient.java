package com.example.product_service.Service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "company-service", url = "http://nginx-server:8080")
public interface CompanyServiceClient {

    @GetMapping("/company/id/{companyId}")
    ResponseEntity<Boolean> validateCompany(@PathVariable UUID companyId);
}
