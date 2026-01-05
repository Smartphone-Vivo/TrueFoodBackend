package dev.TrueFood.repositories;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    """)
    Page<Advertisement> getAdvertisementsWithPagination(@Param("name") String name, PageRequest pageRequest);

//    @Query("""
//    SELECT a FROM Advertisement a
//    WHERE (a.title LIKE CONCAT('%', :name,'%'))
//    AND a.categoryId = :categoryId
//    """)
//    Page<Advertisement> getAdvertisementsByCategory(@Param("name") String name, @Param("categoryId") Long categoryId, PageRequest pageRequest);
//

    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND (a.category = :category OR a.categoryId IN :childrenCategory)
    """)
    Page<Advertisement> getAdvertisementsByCategory(@Param("name") String name,
                                                    @Param("category") Category category,
                                                    @Param("childrenCategory") List<Long> childrenCategory,
                                                    PageRequest pageRequest); //todo

    @Query("""
    SELECT o FROM Order o
    WHERE (o.authorId = :id)
    """)
    Page<Order> getAdverticementByUser(@Param("id") Long id, PageRequest pageRequest);

//    @Query("""
//    SELECT u.favourites FROM User u
//    WHERE (u.id = :id)
//    """)

        @Query("""
        SELECT a FROM User u
        JOIN u.favourites a
        WHERE u.id = :id
        """)
    Page<Advertisement> getFavouritesAdvertisements(@Param("id") Long id, PageRequest pageRequest);
}