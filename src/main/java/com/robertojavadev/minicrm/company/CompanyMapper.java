package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company mapCompanyDtoToCompany(CompanyDto companyDto);

    CompanyDto mapCompanyToCompanyDto(Company company);
}
