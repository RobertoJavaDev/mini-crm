package com.robertojavadev.minicrm.company.dto;

public record CompanyAddDto(String companyName,
                            String email,
                            String logoFilename,
                            String website) {
}
