package dev.TrueFood.entity;

import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.entity.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orders")
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

    //todo на @OneToOne поменять
    @ManyToOne
    @JoinColumn(name = "images_id")
    private Image imagesId;

    //todo
    private String location;

    //todo List

//    //todo enum
//    private String itemType;

    @ManyToOne //todo перетащить в order
    @JoinColumn(name = "users_id", nullable = false, updatable = false, insertable = false)
    private User Author;

    @Column(name="users_id")
    private Long authorId;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    //todo Date
    private String createdAt;

    private boolean enable;


    public Order(Long id, String title, Long authorId, String description, Long categoryId,  int price,  String location, Image imagesId, OrderType orderType, String createdAt, boolean enable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.imagesId = imagesId;
        this.location = location;
        this.authorId = authorId;
        this.orderType = orderType;
        this.createdAt = createdAt;
        this.enable = enable;
    }
}
