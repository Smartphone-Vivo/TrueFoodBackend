package dev.TrueFood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@NamedEntityGraph(
        name = "task-graph",
        attributeNodes = {
                @NamedAttributeNode("workers"),
                @NamedAttributeNode("acceptedWorker")
        }
)

public class Task extends Order {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "task-workers",
            joinColumns = @JoinColumn(name = "tasks_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> workers;

    @ManyToOne(fetch = FetchType.LAZY)
    private User acceptedWorker;

    public Task(Long id, String title, String description, Long authorId, Category category, int price, String location, Image imagesId, boolean enable) {
        super(id, title, authorId, description, category, price, location, imagesId, enable);
    }


}