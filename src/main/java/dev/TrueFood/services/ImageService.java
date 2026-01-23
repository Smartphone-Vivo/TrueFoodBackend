package dev.TrueFood.services;

import dev.TrueFood.dto.UploadResponse;
import dev.TrueFood.exceptions.FailedUploadException;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;


    @PostConstruct
    public void init() throws Exception {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    public List<UploadResponse> addFiles(MultipartFile[] files){
        if(files.length == 0){
            throw new FailedUploadException("failed upload file");
        }

        List<UploadResponse> responses = new ArrayList<>();

        try{
            for(MultipartFile file : files){
                if(!file.isEmpty()){
                    String fileUrl = uploadFile(file);

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

    public String uploadFile(MultipartFile file) {
        try{
        String fileName = generateFileName(file.getOriginalFilename());

            minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

            return getFileUrl(fileName);
        }catch (Exception e){
            throw new FailedUploadException("Failed to upload file: " + e.getMessage());
        }
    }

    public String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString().substring(0, 32)
                + getExtention(originalFileName);
    }

    public String getExtention(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String getFileUrl(String objectName){
        return "http://localhost:9000/" + bucketName + "/" + objectName;
    }

//    public String getFileUrl(String objectName){
//        try {
//            return minioClient.getPresignedObjectUrl(
//                    GetPresignedObjectUrlArgs.builder()
//                            .method(Method.GET)
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .expiry(60*60*24*7)
//                            .build()
//            );
//        } catch (Exception e){
//            throw new FailedUploadException("Failed to get file url: " + e.getMessage());
//        }
//    }

}
