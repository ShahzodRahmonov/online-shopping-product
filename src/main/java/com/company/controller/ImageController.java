package com.company.controller;

import com.company.dto.ImageDTO;
import com.company.servise.ImageServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageServise imageServise;

//    upload an image to the system
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        ImageDTO result = imageServise.saveToSystem(file);
        return ResponseEntity.ok().body(result);
    }

//    image get by id
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        ImageDTO response = imageServise.getById(id);
        return ResponseEntity.ok().body(response);
    }
}
