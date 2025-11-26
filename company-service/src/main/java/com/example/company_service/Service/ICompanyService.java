package com.example.company_service.Service;

import com.example.company_service.Model.Dto.CompanyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ICompanyService {

    ResponseEntity<List<CompanyDto>> getAllCompany();

    ResponseEntity<CompanyDto> getCompanyById(UUID companyId);

    ResponseEntity<CompanyDto> getCompanyByName(String companyName);

    ResponseEntity<String> createCompany(CompanyDto company);

    ResponseEntity<Boolean> validateCompany(UUID companyId);
}
