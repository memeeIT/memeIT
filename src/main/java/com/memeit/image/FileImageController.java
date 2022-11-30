package com.memeit.image;

import com.memeit.common.PageMappingInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.memeit.common.PageMappingInfo.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PageMappingInfo.API_PATH)
public class FileImageController {
    private final FileImageServicesImpl service;

    @GetMapping(GET_IMAGE_PATH)
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String name) throws IOException {
        byte[] bytes = service.downloadImageFromFileSystem(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(bytes);

    }

}