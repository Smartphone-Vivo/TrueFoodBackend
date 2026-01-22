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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "images_id")
    private Image avatar;

    private int rating = 0;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favourites",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "Orders_id")
    )
    private List<Advertisement> favourites;

     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     @JoinColumn(name = "reviews_id")
     private List<Review> reviews;

     private String contacts;

    public User(Long id, String email, Image avatar, Role role, Password password, boolean enable, String fio, int rating, String contacts) {
        super(id, email, role, password, enable);
        this.fio = fio;
        this.avatar = avatar;
        this.rating = rating;
        this.contacts = contacts;
    }

}
