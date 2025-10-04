package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
interface CompanyService {
    CompanyDto createCompany(@NonNull @Valid CompanyDto companyDto);

    List<CompanyDto> findAllCompanies();

    CompanyDto findCompanyById(@NonNull UUID companyId);

    CompanyDto updateCompany(@NonNull UUID companyId, @NonNull @Valid CompanyDto companyDto);

    void deleteCompanyById(@NonNull UUID companyId);
}
