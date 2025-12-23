package dev.TrueFood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Order_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long categoryId;

    private int price;

    //todo
    private String location;

    //todo List
    private Long imagesId;

    //todo enum
    private String itemType;

    private Date createdAt;

    private boolean enable;

}
