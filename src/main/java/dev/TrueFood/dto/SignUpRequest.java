package dev.TrueFood.dto;

import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequest {

    private String imageUrl;
    private String email;
    private String password;
    private String fio;
    private String fileUrl;

//    private Image avatar;

    //todo location

//    private List<Adverticement> ads;

}
