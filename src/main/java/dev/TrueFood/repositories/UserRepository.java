package dev.TrueFood.repositories;

import dev.TrueFood.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
    SELECT DISTINCT u FROM User u
    JOIN FETCH u.avatar
    LEFT JOIN FETCH u.favourites f
    WHERE u.id = :id
""")
    User findByUserId(
            @Param("id") Long id);

}
