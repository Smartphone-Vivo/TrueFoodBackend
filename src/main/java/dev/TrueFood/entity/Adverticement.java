package dev.TrueFood.entity;

import dev.TrueFood.entity.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adverticements")
@Getter
@Setter
@NoArgsConstructor
public class Adverticement extends Order {

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, updatable = false, insertable = false)
    private User Author;

    @Column(name="users_id")
    private Long authorId;

    public Adverticement(Long id, String title, Long authorId, String description, Long categoryId, int price, String location, Image imagesId, String itemType, String createdAt, boolean enable) {
        super(id, title, description, categoryId, price, location, imagesId, itemType, createdAt, enable);
        this.authorId = authorId;
    }



}
