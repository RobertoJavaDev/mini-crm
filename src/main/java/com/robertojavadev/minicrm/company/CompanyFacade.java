package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import com.robertojavadev.minicrm.company.dto.CompanyUpdateDto;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyFacade {
    private final CompanyService companyService;
    private final LogoUploadService logoUploadService;

    public CompanyDto createCompany(@NonNull @Valid CompanyAddDto companyAddDto) {
        return companyService.createCompany(companyAddDto);
    }

    public Page<CompanyDto> findAllCompanies(Pageable pageable) {
        return companyService.findAllCompanies(pageable);
    }

    public CompanyDto findCompanyById(@NonNull UUID companyId) {
        return companyService.findCompanyById(companyId);
    }

    public CompanyDto updateCompany(@NonNull UUID companyId, @NonNull @Valid CompanyUpdateDto companyUpdateDto) {
        return companyService.updateCompany(companyId, companyUpdateDto);
    }

    public void deleteCompany(@NonNull UUID companyId) {
        companyService.deleteCompanyById(companyId);
    }

    public String uploadLogo(MultipartFile file) {
        return logoUploadService.uploadLogo(file);
    }
}
