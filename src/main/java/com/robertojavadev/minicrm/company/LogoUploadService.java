package com.robertojavadev.minicrm.company;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Validated
public interface LogoUploadService {
    String uploadLogo(MultipartFile file);
}
