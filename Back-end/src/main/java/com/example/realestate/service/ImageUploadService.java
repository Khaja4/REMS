package com.example.realestate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageUploadService {

    private final Path root = Paths.get("uploads");

    public String storeImage(MultipartFile file) throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectory(root);
        }
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), this.root.resolve(filename));
        return filename;
    }
}