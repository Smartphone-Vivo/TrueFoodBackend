package dev.TrueFood.services;

import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class MinioService {


    private final MinioClient minioClient;


    @Value("${minio.bucket-name}")
    private String bucketName;


    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

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

    public String uploadFile(MultipartFile file, String folder) throws IOException {
        try{
        String fileName = generateFileName(file.getOriginalFilename());
        String objectName = folder + "/" + fileName;

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
        return getFileUrl(objectName);
        }catch (Exception e){
            throw new IOException(e.getMessage());
        }
    }

    public String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString()
                + getExtention(originalFileName);
    }

    public String getExtention(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String getFileUrl(String objectName){
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(60*60*24*7)
                            .build()
            );
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
