package dev.TrueFood.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Review;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToMany
//    @JsonIgnore
    @JoinTable(
            name = "user_favourites",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "adverticements_id")
    )
    @JsonIgnoreProperties({"author", "favourites"})
    private List<Advertisement> favourites;

     @ManyToOne
     private Review reviews;

    public User(Long id, String email, Image avatar, Role role, Password password, boolean enable, String fio, int rating) {
        super(id, email, role, password, enable);
        this.fio = fio;
        this.avatar = avatar;
        this.rating = rating;
    }

//    public void setFavourites(Advertisement advertisement) {
//        this.favourites.add(advertisement);
//    }

    //todo location

}
