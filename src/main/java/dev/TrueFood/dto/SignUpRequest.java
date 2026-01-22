package dev.TrueFood.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequest {


    @NotBlank(message = "Image is required")
    private String imageUrl;

    @Email
    @NotBlank(message = "Email - kal")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Username is required")
    private String fio;

    @NotBlank(message = "Contacts is required")
    private String contacts;

    //todo location

}
