package dev.TrueFood.entity.users;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Image;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseUser {

    private String fio;

//    @OneToMany
//    @JoinColumn(name = "images_id")
//    private Image avatar; //todo ??? хз сомнительные движухи

    private int rating;

    public User(Long id, String email, Role role, Password password, boolean enable, String fio, int rating) {
        super(id, email, role, password, enable);
        this.fio = fio;
//        this.avatar = avatar;
        this.rating = rating;

    }

    //todo location

//    @OneToMany
//    @JoinColumn(name = "adverticements_id")
//    private List<Adverticement> ads;
}
