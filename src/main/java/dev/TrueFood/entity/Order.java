package dev.TrueFood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Orders")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "order-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "category", subgraph = "category.parent"),
                @NamedAttributeNode("images"),
                @NamedAttributeNode("author")
        },
        subgraphs = {
                @NamedSubgraph(name="category.parent",
                attributeNodes = @NamedAttributeNode("parent"))
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") //todo nullable
    @JsonIgnoreProperties({"parent", "advertisements"})
    private Category category;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "images_id")
    private Image images; //todo поменять название

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User author;

    private Date createdAt;

    private boolean enable;

    public Order(Long id, String title, User author, String description, Category category,  int price,  String location, Image images, boolean enable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.images = images;
        this.location = location;
        this.author = author;
        this.createdAt = new Date();
        this.enable = enable;
    }

}
