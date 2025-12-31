package dev.TrueFood.entity.users;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Image;
import jakarta.persistence.*;
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

    // todo ??? хз сомнительные движухи
    //todo сделать @OneToOne
    //todo eager убрать
    @ManyToOne //todo почитать почему так
    @JoinColumn(name = "images_id")
    private Image avatar;



    private int rating;

    public User(Long id, String email, Image avatar, Role role, Password password, boolean enable, String fio, int rating) {
        super(id, email, role, password, enable);
        this.fio = fio;
        this.avatar = avatar;
        this.rating = rating;

    }

    //todo location

//    @OneToMany
//    @JoinColumn(name = "adverticements_id")
//    private List<Adverticement> ads;
}
