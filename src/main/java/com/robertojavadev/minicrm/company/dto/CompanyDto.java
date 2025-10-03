package com.robertojavadev.minicrm.company.dto;

import java.util.UUID;

public record CompanyDto(UUID id,
                         String name,
                         String email,
                         String logo,
                         String website) {
}
