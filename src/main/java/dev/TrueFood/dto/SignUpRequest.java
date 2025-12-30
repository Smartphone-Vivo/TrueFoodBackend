package dev.TrueFood.dto;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpRequest {

    private String email;

    private String password;

    private String fio;

    private Image avatar;

    private Role role;

    private boolean enable;

    private int rating;

    //todo location

    private List<Adverticement> ads;

}
