package dev.TrueFood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponse {
        private String fileName;
        private String fileUrl;
        private Long fileSize;
        private String contentType;
}
