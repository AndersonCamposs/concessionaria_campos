package com.example.concessionaria_campos.service;

import com.example.concessionaria_campos.dto.PhotoDTO;
import com.example.concessionaria_campos.exception.FileExtensionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class FileStorageService {

    private final Set<String> ACCEPTED_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png");

    @Value("${file.upload-dir}")
    private String baseUploadDir;

    public String saveFile(MultipartFile file, String subFolder) throws IOException {
        Path uploadPath = Paths.get(baseUploadDir, subFolder);

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = "IMG_" + UUID.randomUUID().toString() + fileExtension;
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString().replace("\\", "/");
    }

    public List<PhotoDTO> saveFiles(List<MultipartFile> files, String subFolder) throws IOException {
        Path uploadPath = Paths.get(baseUploadDir, subFolder);

        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        List<PhotoDTO> dtos = new ArrayList<>();
        for (MultipartFile file: files) {
            PhotoDTO dto = new PhotoDTO();

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = "IMG_" + UUID.randomUUID().toString() + fileExtension;
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            dto.setName(fileName);
            dto.setPath(filePath.toString().replace("\\", "/"));
            dtos.addLast(dto);
        }

        return dtos;
    }

    public void deleteFiles(List<PhotoDTO> files) {
        for (PhotoDTO photo: files) {
            File file = new File(photo.getPath());
            if (file.exists()) {
                if(!file.delete()) {
                    throw new RuntimeException("Erro ao deletar o arquivo: " + photo.getName());
                }
            } else {
                throw new RuntimeException("Erro ao encontrar o arquivo: " + photo.getName());
            }
        }
    }

    public void validate(MultipartFile file) {
        if (file == null) {
            return;
        }
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        if (!ACCEPTED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            List<String> withErrors = new ArrayList<>();
            withErrors.add(originalFileName);
            throw new FileExtensionException("Arquivo com extensão inadequada.", withErrors);
        }
    }

    public void validate(List<MultipartFile> files) {
        if (files.isEmpty()) {
            return;
        }
        List<String> withErrors = new ArrayList<>();
        for (MultipartFile file: files) {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            if (!ACCEPTED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
                withErrors.add(originalFileName);
            }
        }

        if (!withErrors.isEmpty()) {
            throw new FileExtensionException("Um ou mais arquivos possuem extensão inadequada.", withErrors);
        }
    }
}
