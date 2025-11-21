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
    public ResponseEntity<List<CompanyDto>> getAllCompany() {
        List<CompanyEntity> companies = companyRepository.findAll();

        return ResponseEntity.ok(companies.stream().map(companyDtoFactory::createCompanyDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<CompanyDto> getCompanyById(UUID companyId) throws RuntimeException {
        CompanyEntity company = companyRepository.findCompanyById(companyId);

        if (company == null) {
            throw new RuntimeException("Компании с таким id не существует!");
        }

        return ResponseEntity.ok(companyDtoFactory.createCompanyDto(company));
    }

    @Override
    public ResponseEntity<CompanyDto> getCompanyByName(String companyName) throws RuntimeException {
        CompanyEntity company = companyRepository.findCompanyByName(companyName);

        if (company == null) {
            throw new RuntimeException("Компании с таким name не существует!");
        }

        return ResponseEntity.ok(companyDtoFactory.createCompanyDto(company));
    }

    @Override
    public ResponseEntity<String> createCompany(CompanyDto company) throws RuntimeException {
        CompanyEntity newCompany = companyEntityFactory.createCompanyEntity(company);

        CompanyEntity companyByName = companyRepository.findCompanyByName(newCompany.getCompanyName());

        if (companyByName != null) {
            throw new RuntimeException("Компания с таким name уже существует!");
        }

        companyRepository.saveAndFlush(newCompany);

        return ResponseEntity.ok("Company created!");
    }

}
