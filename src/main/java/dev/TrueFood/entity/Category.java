package dev.TrueFood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    Category parent;

    String name;

    //todo ???
    @JsonIgnore
    List<Long> childrenId = new ArrayList<>();

//    //todo где хранится(мое)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn("category_id")
//    List<Category> childrenId;


    public Category(Category parent, String name) {

        this.parent = parent;
        this.name = name;
        this.childrenId = new ArrayList<>();
    }

}
