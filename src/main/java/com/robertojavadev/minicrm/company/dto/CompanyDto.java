package com.robertojavadev.minicrm.company.dto;

import java.util.UUID;

public record CompanyDto(UUID id,
                         String companyName,
                         String email,
                         String logoFilename,
                         String website) {
}
