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

    //1запрос
    @Override
    @EntityGraph(value = "order-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Advertisement> findById(@Param("id") Long id);

    @EntityGraph(value = "order-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND (a.categoryId = :categoryId OR a.categoryId IN :childrenCategory)
    """)
    Page<Advertisement> getAdvertisementsByCategory(@Param("name") String name,
                                                    @Param("categoryId") Long categoryId,
                                                    @Param("childrenCategory") List<Long> childrenCategory,
                                                    PageRequest pageRequest); //todo
    //1 запрос
    @EntityGraph(value = "order-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.authorId = :id)
    """)
    Page<Advertisement> getAdverticementByUser(@Param("id") Long id, PageRequest pageRequest);

        @EntityGraph(value = "order-graph", type = EntityGraph.EntityGraphType.FETCH)
        @Query("""
        SELECT a FROM User u
        JOIN u.favourites a
        WHERE u.id = :id
        """)
    Page<Advertisement> getFavouritesAdvertisements(@Param("id") Long id, PageRequest pageRequest);
}