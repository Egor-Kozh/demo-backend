package com.example.company_service.Model.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Builder
@Data
public class CompanyDto {

    private UUID companyId;

    @NonNull
    private String companyName;
}
