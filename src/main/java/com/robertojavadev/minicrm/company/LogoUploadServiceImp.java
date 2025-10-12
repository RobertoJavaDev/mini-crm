package com.robertojavadev.minicrm.company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
class LogoUploadServiceImp implements LogoUploadService {

    @Value("${app.uploads.base-dir:/app/uploads}")
    private String baseDir;

    @Value("${app.uploads.logos-subdir:logos}")
    private String logosSubdir;

    public String uploadLogo(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Nieprawidłowy typ pliku. Dozwolone są tylko obrazy.");
        }

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null || image.getWidth() < 100 || image.getHeight() < 100) {
                throw new IllegalArgumentException("Logo musi mieć co najmniej 100x100 pikseli.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Nie można odczytać pliku graficznego.", e);
        }

        String filename = UUID.randomUUID() + "_" + Path.of(file.getOriginalFilename()).getFileName();

        Path targetDir = Paths.get(baseDir, logosSubdir);
        Path targetPath = targetDir.resolve(filename);

        try {
            Files.createDirectories(targetDir);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Nie udało się zapisać pliku logo", e);
        }

        return "/uploads/" + logosSubdir + "/" + filename;
    }
}
