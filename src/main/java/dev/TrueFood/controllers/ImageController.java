package dev.TrueFood.controllers;


import dev.TrueFood.dto.UploadResponse;
import dev.TrueFood.exceptions.FailedUploadException;
import dev.TrueFood.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/files")
@CrossOrigin
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public List<UploadResponse> addFiles(
            @RequestParam("file") MultipartFile[] files
    ){
        return imageService.addFiles(files);

    }

}
