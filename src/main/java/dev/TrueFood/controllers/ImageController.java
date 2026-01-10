package dev.TrueFood.controllers;


import dev.TrueFood.dto.UploadResponse;
import dev.TrueFood.repositories.ImageRepository;
import dev.TrueFood.services.MinioService;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/files")
@CrossOrigin
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ImageController {

    private final MinioService minioService;

    @PostMapping("/upload")
    public ResponseEntity<List<UploadResponse>> uploadFile(

            //todo логику из контроллера убрать

            @RequestParam("file") MultipartFile[] files){
        if(files.length == 0){
            return ResponseEntity.badRequest().build();
        }

        List<String> fileUrls = new ArrayList<>();
        List<UploadResponse> responses = new ArrayList<>();

        try{
            for(MultipartFile file : files){
                if(!file.isEmpty()){
                    String fileUrl = minioService.uploadFile(file);

                    UploadResponse uploadResponse = new UploadResponse(
                            file.getOriginalFilename(),
                            fileUrl,
                            file.getSize(),
                            file.getContentType()
                    );
                    responses.add(uploadResponse);
                    fileUrls.add(fileUrl);
                }
            }
            return ResponseEntity.ok(responses);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
