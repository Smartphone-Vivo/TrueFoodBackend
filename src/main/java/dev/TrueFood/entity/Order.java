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

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, updatable = false, insertable = false)
    private Category category;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    private int price;

    @ManyToOne
    @JoinColumn(name = "images_id")
    private Image imagesId;

    //todo
    private String location;

    //todo List

    //todo enum
    private String itemType;

    //todo Date
    private String createdAt;

    private boolean enable;


    public Order(Long id, String title, String description, Long categoryId, int price, String location, Image imagesId, String itemType, String createdAt, boolean enable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.location = location;
        this.imagesId = imagesId;
        this.itemType = itemType;
        this.createdAt = createdAt;
        this.enable = enable;
    }
}
