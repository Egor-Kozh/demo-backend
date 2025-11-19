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
        return companyService.getCompanyById(companyId);
    }

    @GetMapping(path = "/name/{companyName}")
    public ResponseEntity<?> getCompanyByName(@PathVariable String companyName) {
        return companyService.getCompanyByName(companyName);
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

}
