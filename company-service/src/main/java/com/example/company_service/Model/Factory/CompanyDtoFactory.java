package com.example.company_service.Model.Factory;

import com.example.company_service.Model.Dto.CompanyDto;
import com.example.company_service.Model.Entity.CompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoFactory {

    public CompanyDto createCompanyDto(CompanyEntity company) {
        return CompanyDto.builder()
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .build();
    }
}
