package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import com.robertojavadev.minicrm.company.dto.CompanyUpdateDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
interface CompanyService {
    CompanyDto createCompany(@NonNull @Valid CompanyAddDto companyAddDto);

    Page<CompanyDto> findAllCompanies(Pageable pageable);

    CompanyDto findCompanyById(@NonNull UUID companyId);

    CompanyDto updateCompany(@NonNull UUID companyId, @NonNull @Valid CompanyUpdateDto companyUpdateDto);

    void deleteCompanyById(@NonNull UUID companyId);
}
