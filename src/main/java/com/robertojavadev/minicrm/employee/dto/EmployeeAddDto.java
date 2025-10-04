package com.robertojavadev.minicrm.employee.dto;

import com.robertojavadev.minicrm.company.Company;

public record EmployeeAddDto(
        String firstName,
        String lastName,
        Company company,
        String email,
        String phone
) {
}
