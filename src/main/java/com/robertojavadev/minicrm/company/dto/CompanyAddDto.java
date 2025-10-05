package com.robertojavadev.minicrm.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyAddDto(
        @NotBlank()
        @Size(min = 2, max = 255)
        String companyName,

        @Email()
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
