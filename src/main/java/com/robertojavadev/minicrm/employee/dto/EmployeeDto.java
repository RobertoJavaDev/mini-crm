package com.robertojavadev.minicrm.employee.dto;

import com.robertojavadev.minicrm.company.Company;

import java.util.UUID;

public record EmployeeDto(
        UUID id,
        String firstName,
        String lastName,
        Company company,
        String email,
        String phone
) {
}
