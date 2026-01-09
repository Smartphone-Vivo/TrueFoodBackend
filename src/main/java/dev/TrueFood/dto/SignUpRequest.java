package dev.TrueFood.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequest {

    private String imageUrl;
    private String email;
    private String password;
    private String fio;
    private String contacts;

    //todo location

}
