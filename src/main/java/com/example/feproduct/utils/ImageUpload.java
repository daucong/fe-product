package com.example.feproduct.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {
    public final String UPLOAD_FOLDER = "D:\\image";
    public boolean uploadImage(MultipartFile imageProduct){
        boolean isUpload = false;
        try {
            Files.copy(imageProduct.getInputStream(),
                    Paths.get(UPLOAD_FOLDER+ File.separator, imageProduct.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isUpload;
    }
}
