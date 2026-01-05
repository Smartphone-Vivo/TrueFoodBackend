package dev.TrueFood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Category parent;

    String name;

    @JsonIgnore
    List<Long> childrenId = new ArrayList<>();

    public Category(Long id, Category parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.childrenId = new ArrayList<>();
    }

    public Category(Category parent, String name) {

        this.parent = parent;
        this.name = name;
        this.childrenId = new ArrayList<>();
    }

}
