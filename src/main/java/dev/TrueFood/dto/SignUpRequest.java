package dev.TrueFood.dto;

import dev.TrueFood.entity.users.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequest {

    private String email;
    private String password;
    private String fio;

//    private Image avatar;

    //todo location

//    private List<Adverticement> ads;

}
