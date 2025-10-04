package com.robertojavadev.minicrm._config;

import com.robertojavadev.minicrm.company.Company;
import com.robertojavadev.minicrm.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StringToCompanyConverter implements Converter<String, Company> {

    private final CompanyRepository companyRepository;

    @Override
    public Company convert(String source) {
        try {
            UUID id = UUID.fromString(source);
            return companyRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
