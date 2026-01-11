package dev.TrueFood.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adverticements")
@Getter
@Setter
@NoArgsConstructor

public class Advertisement extends Order {

    public Advertisement(Long id, String title, String description, User author, Category category, int price, String location, Image imagesId, boolean enable) {
        super(id, title, author, description, category, price, location, imagesId, enable);
    }
}
