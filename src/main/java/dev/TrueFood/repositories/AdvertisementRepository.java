package dev.TrueFood.repositories;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("""
    SELECT a FROM Advertisement a
    JOIN FETCH a.author
    JOIN FETCH a.images
    JOIN FETCH a.category
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND (a.category = :category OR a.category IN :childrenCategory)
    """)
    Page<Advertisement> getAdvertisementsByCategory(@Param("name") String name,
                                                    @Param("category") Category category,
                                                    @Param("childrenCategory") List<Category> childrenCategory,
                                                    PageRequest pageRequest);

    @Query("""
    SELECT a FROM Advertisement a
    JOIN FETCH a.images
    WHERE (a.author.id = :id)
    """)
    Page<Advertisement> getAdverticementByUser(@Param("id") Long id, PageRequest pageRequest);

    @Query("""
    SELECT a FROM User u
    JOIN u.favourites a
    JOIN FETCH a.images
    WHERE u.id = :id
    """)
    Page<Advertisement> getFavouritesAdvertisements(@Param("id") Long id, PageRequest pageRequest);

    @Query("""
    SELECT a FROM Advertisement a
    JOIN FETCH a.images
    WHERE a.id = :id
    """)
    Optional<Advertisement> findAdvertisementById(@Param("id") Long id);
}