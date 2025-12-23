package dev.TrueFood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "adverticements")
@Getter
@Setter
@NoArgsConstructor
public class Adverticement extends Order {

    public Adverticement(Long id, String title, String description, Long categoryId, int price, String location, Long imagesId, String itemType, String createdAt, boolean enable) {
        super(id, title, description, categoryId, price, location, imagesId, itemType, createdAt, enable);
    }



}
