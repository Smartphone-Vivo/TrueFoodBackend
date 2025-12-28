package dev.TrueFood.controllers;


import dev.TrueFood.dto.UploadResponse;
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


    public MinioController(MinioClient minioClient, MinioService minioService) {
        this.minioClient = minioClient;
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("adverticementId") Long adverticementId){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        try{
            String folder = "item_" + adverticementId;
            String fileUrl = minioService.uploadFile(file, folder);

            UploadResponse response = new UploadResponse(
                    file.getOriginalFilename(),
                    fileUrl,
                    file.getSize(),
                    file.getContentType()
            );

            return ResponseEntity.ok(response);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}
