package com.app_red_social.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/foto")
@Tag(name = "Fotos")
public class FotosContoller {
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            // Generate unique filename
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Path where the file will be saved
            Path uploadPath = Paths.get("uploads/groups").resolve(fileName);

            // Create folders if they don't exist
            Files.createDirectories(uploadPath.getParent());

            // Save the file
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

            // Return the public path
            return ResponseEntity.ok("/uploads/groups/" + fileName);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error uploading file: " + e.getMessage());
        }
    }
}
