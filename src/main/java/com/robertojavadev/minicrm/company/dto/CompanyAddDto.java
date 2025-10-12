package com.robertojavadev.minicrm.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyAddDto(
        @NotBlank()
        @Size(min = 2, max = 255)
        String companyName,

        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Podaj poprawny adres e-mail (np. jan.kowalski@example.com)"
        )
        @Size(max = 255)
        String email,

        @Size(max = 255)
        String logoFilename,

        @Pattern(
                regexp = "^(|https?://[\\w.-]+\\.[a-z]{2,}([/\\w .-]*)*/?)$",
                message = "Podaj poprawny adres strony internetowej"
        )
        @Size(max = 255)
        String website) {
}
