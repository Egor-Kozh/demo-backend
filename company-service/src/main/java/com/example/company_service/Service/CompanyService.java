package com.example.company_service.Service;

import com.example.company_service.Model.Dto.CompanyDto;
import com.example.company_service.Model.Entity.CompanyEntity;
import com.example.company_service.Model.Factory.CompanyDtoFactory;
import com.example.company_service.Model.Factory.CompanyEntityFactory;
import com.example.company_service.Repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyDtoFactory companyDtoFactory;

    private final CompanyEntityFactory companyEntityFactory;

    @Override
    public ResponseEntity<?> getAllCompany() {
        List<CompanyEntity> companies = companyRepository.findAll();

        return ResponseEntity.ok(companies.stream().map(companyDtoFactory::createCompanyDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> getCompanyById(UUID companyId) {
        CompanyEntity company = companyRepository.findCompanyById(companyId);

        return ResponseEntity.ok(companyDtoFactory.createCompanyDto(company));
    }

    @Override
    public ResponseEntity<?> getCompanyByName(String companyName) {
        CompanyEntity company = companyRepository.findCompanyByName(companyName);

        return ResponseEntity.ok(companyDtoFactory.createCompanyDto(company));
    }

    @Override
    public ResponseEntity<?> createCompany(CompanyDto company) {
        System.out.println(company);
        CompanyEntity newCompany = companyEntityFactory.createCompanyEntity(company);

        System.out.println(newCompany);

        companyRepository.saveAndFlush(newCompany);

        return ResponseEntity.ok("Company created!");
    }

}
