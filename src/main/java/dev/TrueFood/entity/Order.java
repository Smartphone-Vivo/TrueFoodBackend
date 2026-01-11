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
                @NamedAttributeNode("imagesId"),
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
    private Image imagesId; //todo поменять название

    private String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private User author;

    @Column(name="users_id")
    private Long authorId;

    private Date createdAt;

    private boolean enable;

    public Order(Long id, String title, Long authorId, String description, Category category,  int price,  String location, Image imagesId, boolean enable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imagesId = imagesId;
        this.location = location;
        this.authorId = authorId;
        this.createdAt = new Date();
        this.enable = enable;
    }

}
