package com.example.company_service.Repository;

import com.example.company_service.Model.Entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    @Query(value = "select * from company\n" +
            "where company_id = :companyId;", nativeQuery = true)
    CompanyEntity findCompanyById(UUID companyId);

    @Query(value = "select * from company\n" +
            "where company_name = :companyName;", nativeQuery = true)
    CompanyEntity findCompanyByName(String companyName);
}
