package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    public static final String COMPANY_WITH_ID_DOES_NOT_EXIST = "Company with id: %s does not exist";
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.mapCompanyDtoToCompany(companyDto);
        return companyMapper.mapCompanyToCompanyDto(companyRepository.save(company));
    }

    public List<CompanyDto> findAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::mapCompanyToCompanyDto).collect(Collectors.toList());
    }

    public CompanyDto findCompanyById(UUID id) {
        return companyMapper.mapCompanyToCompanyDto(companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(COMPANY_WITH_ID_DOES_NOT_EXIST + id)));
    }

    public CompanyDto updateCompany(UUID id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(COMPANY_WITH_ID_DOES_NOT_EXIST, id)));

        company.setCompanyName(companyDto.companyName());
        company.setEmail(companyDto.email());
        company.setLogoFilename(companyDto.logoFilename());
        company.setWebsite(companyDto.website());

        return companyMapper.mapCompanyToCompanyDto(companyRepository.save(company));
    }

    public void deleteCompanyById(UUID id) {
        companyRepository.deleteById(id);
    }
}
