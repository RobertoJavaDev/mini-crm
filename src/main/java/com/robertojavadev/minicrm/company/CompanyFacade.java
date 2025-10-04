package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class CompanyFacade {
    private final CompanyService companyService;

    public CompanyDto createCompany(@NonNull @Valid CompanyAddDto companyAddDto) {
        return companyService.createCompany(companyAddDto);
    }

    public List<CompanyDto> findAllCompanies() {
        return companyService.findAllCompanies();
    }

    public CompanyDto findCompanyById(@NonNull UUID companyId) {
        return companyService.findCompanyById(companyId);
    }

    public CompanyDto updateCompany(@NonNull UUID companyId, @NonNull @Valid CompanyDto companyDto) {
        return companyService.updateCompany(companyId, companyDto);
    }

    public void deleteCompany(@NonNull UUID companyId) {
        companyService.deleteCompanyById(companyId);
    }
}
