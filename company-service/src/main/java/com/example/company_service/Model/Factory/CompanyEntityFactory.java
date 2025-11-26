package com.example.company_service.Model.Factory;

import com.example.company_service.Model.Dto.CompanyDto;
import com.example.company_service.Model.Entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyEntityFactory {

    public CompanyEntity createCompanyEntity(CompanyDto company) {
        return CompanyEntity.builder()
                .companyName(company.getCompanyName())
                .build();
    }
}
