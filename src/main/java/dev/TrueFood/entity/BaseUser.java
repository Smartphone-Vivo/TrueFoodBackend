package dev.TrueFood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.TrueFood.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "base_users")
@Inheritance(strategy = InheritanceType.JOINED) //todo почитать
@Getter
@Setter //todo data заменяет ли это
@NoArgsConstructor
@AllArgsConstructor
public class BaseUser{

    @Id
    @GeneratedValue()
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)//todo про это почитать
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id", nullable = false)
    @JsonIgnore
    private Password password;

    private boolean enable;
}