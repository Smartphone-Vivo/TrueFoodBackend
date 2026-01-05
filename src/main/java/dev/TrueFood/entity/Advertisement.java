package dev.TrueFood.entity;

import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.entity.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adverticements")
@Getter
@Setter
@NoArgsConstructor
//todo RequiredArgsConstructor можно впихнуть
public class Advertisement extends Order {

    public Advertisement(Long id, String title, String description, Long authorId, Long categoryId, int price, String location, Image imagesId, OrderType orderType, boolean enable) {
        super(id, title, authorId, description, categoryId, price, location, imagesId, orderType, enable);
    }
}
