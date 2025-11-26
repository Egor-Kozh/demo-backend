package com.example.company_service.Controller;

import com.example.company_service.Model.Dto.CompanyDto;
import com.example.company_service.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAllCompany() {
        return companyService.getAllCompany();
    }

    @GetMapping(path = "/id/{companyId}")
    public ResponseEntity<?> getCompanyById(@PathVariable UUID companyId) {
        try {
            return companyService.getCompanyById(companyId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/name/{companyName}")
    public ResponseEntity<?> getCompanyByName(@PathVariable String companyName) {
        try {
            return companyService.getCompanyByName(companyName);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
        try {
            return companyService.createCompany(companyDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/check/{companyId}")
    public ResponseEntity<?> checkCompany(@PathVariable UUID companyId) {
        try {
            return companyService.validateCompany(companyId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
