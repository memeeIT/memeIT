package com.memeit.image;


import com.memeit.post.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;


@Service
public class FileImageServicesImpl {

    @Autowired
    private FileImageRepository fileDataRepository;


    /**
     * You need to create a disk directory and the photos will be shared there.
     * Below is a link to the catalogue
     */
    private final String FOLDER_PATH = "C:/Users/Adrian/Desktop/api/";


    public String uploadImageToFileSystem(MultipartFile file, PostDto postDto) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileImage fileData = fileDataRepository.save(FileImage.builder()

                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());
        postDto.setFileImage(fileData);
        file.transferTo(new File(filePath));

        if (fileData == null) {
            throw  new FileNotFoundException("No photo added");
        }
        return filePath;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileImage> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
    public byte[] downloadImageFromId(Long id) throws IOException {
        Optional<FileImage> fileData = fileDataRepository.findById(id);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}