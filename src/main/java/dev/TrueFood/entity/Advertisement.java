package dev.TrueFood.entity;

import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.entity.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adverticements")
@Getter
@Setter
@NoArgsConstructor //todo RequiredArgsConstructor можно впихнуть
public class Advertisement extends Order {


}
