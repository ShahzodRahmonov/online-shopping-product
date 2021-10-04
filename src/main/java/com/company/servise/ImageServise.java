package com.company.servise;

import com.company.converter.ImageConverter;
import com.company.dto.ImageDTO;
import com.company.entity.ImageEntity;
import com.company.exceptions.ImageNotFoundException;
import com.company.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServise {
    @Autowired
    private ImageRepository imageRepository;

    @Value("${image.folder.url}")
    private String imageFolderUrl;
    @Value("${image.url}")
    private String imageUrl;

    public ImageDTO saveToSystem(MultipartFile file) {
        try {
            String filePath = getYmDString(); // YYYY/MM/DD
            String fileType = file.getContentType().split("/")[1]; // png, jpg, jpeg
            String fileToken = UUID.randomUUID().toString();
            String fileUrl = filePath + "/" + fileToken + "." + fileType; // sdasdasdasdasdas.png
            // YYYY/MM/DD/ + adsadasdasdasda + . + png
            File folder = new File(imageFolderUrl + filePath); //  uploads/YYYY/MM/DD
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // save to system
            Path path = Paths.get(imageFolderUrl + fileUrl);

            Files.copy(file.getInputStream(), path);

            ImageDTO dto = this.createImage(file, filePath, fileType, fileToken);

            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public static String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day + "/";
    }

    private ImageDTO createImage(MultipartFile file, String filePath, String fileType, String fileToken) {
        long size = file.getSize();
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setType(fileType);
        imageEntity.setSize(size);
        imageEntity.setPath(filePath);
        imageEntity.setToken(fileToken);
        imageEntity.setCreatedDate(LocalDateTime.now());
        imageEntity.setUrl(imageUrl + fileToken);
//        try {
//            imageEntity.setContent(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        this.imageRepository.save(imageEntity);

        ImageDTO dto = new ImageDTO();
        dto.setPath(filePath);
        dto.setType(fileType);
        dto.setSize(size);
        dto.setToken(fileToken);
        dto.setUrl(imageEntity.getUrl());
        dto.setId(imageEntity.getId());
        return dto;

    }


//    image getById

    public ImageDTO getById(Integer id){

        Optional<ImageEntity> optional = imageRepository.findById(id);

        if (!optional.isPresent()) {
            throw new ImageNotFoundException("Image not found!!!");
        }

        ImageEntity entity = optional.get();
        return ImageConverter.toDTO(entity);
    }


}
