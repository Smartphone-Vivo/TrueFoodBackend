package dev.TrueFood.entity.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id", nullable = false)
    private Password password;

    private boolean enable;
}
