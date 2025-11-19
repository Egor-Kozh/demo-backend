package com.example.company_service.Service;

import com.example.company_service.Model.Dto.CompanyDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ICompanyService {

    ResponseEntity<?> getAllCompany();

    ResponseEntity<?> getCompanyById(UUID companyId);

    ResponseEntity<?> getCompanyByName(String companyName);

    ResponseEntity<?> createCompany(CompanyDto company);
}
