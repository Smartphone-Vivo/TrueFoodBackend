package dev.TrueFood.repositories;

import dev.TrueFood.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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


    @Query("""
    SELECT u FROM User u
    JOIN FETCH u.avatar
    LEFT JOIN FETCH u.favourites f
    """)
    Page<User> getAllUsers (String name,
                            PageRequest pageRequest);

    @Query("""
    SELECT COUNT(f) > 0 FROM User u
    JOIN u.favourites f WHERE u.id = :userId
    AND f.id = :advId
""")
    boolean isAdvertisementIdFavourites(
            @Param("userId") Long userId,
            @Param("advId") Long advId);

    @Query("""
    SELECT COUNT(u) > 0
    FROM User u
    WHERE u.email = :email
    """)
    boolean existsByEmail(@Param("email") String email);


    @Modifying
    @Query(value = """
    INSERT INTO user_favourites (users_id, orders_id)
    VALUES(:userId, :advId)
    """, nativeQuery = true)
    void addToFavouritesNative(
            @Param("userId") Long userId,
            @Param("advId") Long advId
    );

    @Modifying
    @Query(value = """
        DELETE FROM user_favourites
        WHERE users_id = :userId AND orders_id = :advId
    """, nativeQuery = true)
    void removeFromFavouritesNative(
            @Param("userId") Long userId,
            @Param("advId") Long advId
    );


    @Modifying
    @Query(value = """
    UPDATE users u
    SET rating = ROUND((
        SELECT AVG(r.rating)
        FROM reviews r
        WHERE r.user_id = :userId
    ))
    WHERE u.id = :userId
""", nativeQuery = true)
    void updateUserRating(@Param("userId") Long userId);



}
