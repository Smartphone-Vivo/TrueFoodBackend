package dev.TrueFood.controllers;


import dev.TrueFood.dto.UploadResponse;
import dev.TrueFood.exceptions.FailedUploadException;
import dev.TrueFood.services.MinioService;
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

    private final MinioService minioService;

    @PostMapping("/upload")
    public List<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile[] files
    ){
        if(files.length == 0){
           throw new FailedUploadException("failed upload file");
        }

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
                }
            }
            return responses;

        }
        catch (FailedUploadException e){
            throw new FailedUploadException(e.getMessage());
        }

    }

}
