package dev.TrueFood.entity;

import dev.TrueFood.entity.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends Order {

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false, updatable = false, insertable = false)
    private User Author;

    @Column(name="users_id")
    private Long authorId;

    @ManyToMany
    @JoinTable(
            name = "task-workers",
            joinColumns = @JoinColumn(name = "tasks_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> workers;


}
