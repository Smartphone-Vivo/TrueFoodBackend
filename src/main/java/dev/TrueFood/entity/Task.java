package dev.TrueFood.entity;

import dev.TrueFood.dto.TaskResponse;
import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.entity.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends Order {

    @ManyToMany
    @JoinTable(
            name = "task-workers",
            joinColumns = @JoinColumn(name = "tasks_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> workers;

    public Task(Long id, String title, String description, Long authorId, Long categoryId, int price, String location, Image imagesId, OrderType orderType, String createdAt, boolean enable) {
        super(id, title, authorId, description, categoryId, price, location, imagesId, orderType, createdAt, enable);
    }

}
