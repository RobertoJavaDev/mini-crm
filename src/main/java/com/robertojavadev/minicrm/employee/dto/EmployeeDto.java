package com.robertojavadev.minicrm.employee.dto;

import com.robertojavadev.minicrm.company.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EmployeeDto(
        @NotNull
        UUID id,

        @NotBlank(message = "Imię jest wymagane")
        @Size(max = 100, message = "Imię nie może przekraczać 100 znaków")
        String firstName,

        @NotBlank(message = "Nazwisko jest wymagane")
        @Size(max = 100, message = "Nazwisko nie może przekraczać 100 znaków")
        String lastName,

        @NotNull(message = "Firma jest wymagana")
        Company company,

        @Email()
        @Size(max = 255)
        String email,

        @Pattern(
                regexp = "^(\\+48)?\\d{9}$",
                message = "Nieprawidłowy format numeru telefonu"
        )
        String phone
) {
}
