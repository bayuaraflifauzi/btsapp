package com.bekasideveloper.btsapp.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploader {
    private final String dokumenPath = "/home/user/documen_ajuan/";
    public void uploadDocuments(MultipartFile fileSurat, String fileName) {
        byte[] bytes = null;

        try {
            bytes = fileSurat.getBytes();

            Path path
                    = Paths.get(
                            dokumenPath
                            +fileName+"."
                            +fileSurat.getOriginalFilename().split("\\.")[1]);


            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
