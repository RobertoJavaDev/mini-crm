package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import com.robertojavadev.minicrm.company.dto.CompanyUpdateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CompanyServiceImpl implements CompanyService {

    public static final String COMPANY_WITH_ID_DOES_NOT_EXIST = "Company with id: %s does not exist";
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDto createCompany(@NonNull @Valid CompanyAddDto companyAddDto) {
        Company company = companyMapper.mapCompanyAddDtoToCompany(companyAddDto);
        return companyMapper.mapCompanyToCompanyDto(companyRepository.save(company));
    }

    @Override
    public Page<CompanyDto> findAllCompanies(Pageable pageable) {
        List<Company> companies = companyRepository.findAll();
        return companyRepository.findAll(pageable)
                .map(companyMapper::mapCompanyToCompanyDto);
    }

    @Override
    public CompanyDto findCompanyById(@NonNull UUID id) {
        return companyMapper.mapCompanyToCompanyDto(companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(COMPANY_WITH_ID_DOES_NOT_EXIST, id))));
    }

    @Override
    public CompanyDto updateCompany(@NonNull UUID id, @NonNull @Valid CompanyUpdateDto companyUpdateDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(COMPANY_WITH_ID_DOES_NOT_EXIST, id)));

        company.setCompanyName(companyUpdateDto.companyName());
        company.setEmail(companyUpdateDto.email());
        company.setLogoFilename(companyUpdateDto.logoFilename());
        company.setWebsite(companyUpdateDto.website());

        return companyMapper.mapCompanyToCompanyDto(companyRepository.save(company));
    }

    @Override
    public void deleteCompanyById(@NonNull UUID id) {
        companyRepository.deleteById(id);
    }
}
