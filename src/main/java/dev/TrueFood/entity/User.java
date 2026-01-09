package dev.TrueFood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.TrueFood.enums.Role;
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
    //todo почитать почему так
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "images_id")
    private Image avatar;

    private int rating;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "user_favourites",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "Orders_id")
    )
    @JsonIgnoreProperties({"author", "favourites"})
    private List<Advertisement> favourites;

     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     @JsonIgnore
     @JoinColumn(name = "reviews_id")
     private List<Review> reviews;

     @JsonIgnore
     private String contacts;

    public User(Long id, String email, Image avatar, Role role, Password password, boolean enable, String fio, int rating) {
        super(id, email, role, password, enable);
        this.fio = fio;
        this.avatar = avatar;
        this.rating = rating;
    }

    //todo location

}
