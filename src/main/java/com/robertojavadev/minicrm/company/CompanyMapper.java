package com.robertojavadev.minicrm.company;

import com.robertojavadev.minicrm.company.dto.CompanyAddDto;
import com.robertojavadev.minicrm.company.dto.CompanyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface CompanyMapper {
    Company mapCompanyDtoToCompany(CompanyDto companyDto);

    Company mapCompanyAddDtoToCompany(CompanyAddDto companyAddDto);

    CompanyDto mapCompanyToCompanyDto(Company company);
}
