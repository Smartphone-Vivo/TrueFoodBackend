package dev.TrueFood.controllers;


import dev.TrueFood.dto.UploadResponse;
import dev.TrueFood.entity.Image;
import dev.TrueFood.repositories.ImageRepository;
import dev.TrueFood.services.MinioService;
import io.minio.MinioClient;
import io.minio.UploadPartResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/files")
@CrossOrigin

public class MinioController {


    private final MinioClient minioClient;
    private final MinioService minioService;
    private final ImageRepository imageRepository;


    public MinioController(MinioClient minioClient, MinioService minioService, ImageRepository imageRepository) {
        this.minioClient = minioClient;
        this.minioService = minioService;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        try{

            String fileUrl = minioService.uploadFile(file);

            UploadResponse response = new UploadResponse(
                    file.getOriginalFilename(),
                    fileUrl,
                    file.getSize(),
                    file.getContentType()
            );

            Image image = new Image(null, fileUrl);

            imageRepository.save(image); //todo

            return ResponseEntity.ok(response);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}
