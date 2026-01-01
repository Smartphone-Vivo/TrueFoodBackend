package dev.TrueFood.services;

import dev.TrueFood.entity.Image;
import dev.TrueFood.repositories.ImageRepository;
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
    private final ImageRepository imageRepository;

    @Value("${minio.bucket-name}")
    private String bucketName;


    public MinioService(MinioClient minioClient, ImageRepository imageRepository) {
        this.minioClient = minioClient;
        this.imageRepository = imageRepository;
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

    public String uploadFile(MultipartFile file) throws IOException {
        try{
        String fileName = generateFileName(file.getOriginalFilename());
        String objectName = fileName;

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        String fileUrl = getFileUrl(objectName);

        return fileUrl;
        }catch (Exception e){
            throw new IOException(e.getMessage());
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
