package dev.TrueFood.entity;

import dev.TrueFood.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseUser {

    public Admin(Long id, String email, Role role, Password password, boolean enabled) {
        super(id, email, role, password, enabled);
    }



}