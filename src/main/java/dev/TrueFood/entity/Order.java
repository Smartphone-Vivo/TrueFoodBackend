package dev.TrueFood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Order_table")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;

    private Long categoryId;

    private int price;

    //todo
    private String location;

    //todo List
    private Long imagesId;

    //todo enum
    private String itemType;

    //todo Date
    private String createdAt;

    private boolean enable;


}
