package dev.TrueFood.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponse {

        @NotBlank
        private String fileName;

        @NotBlank
        private String fileUrl;

        @NotBlank
        private Long fileSize;

        @NotBlank
        private String contentType;
}
