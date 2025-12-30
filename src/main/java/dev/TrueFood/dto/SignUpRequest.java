package dev.TrueFood.dto;

import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpRequest {

    private Long id;

    private String email;

    private Role role;

    private String password;

    private boolean enable;

    private String fio;

    private int rating;

//    private Image avatar;






    //todo location

//    private List<Adverticement> ads;

}
