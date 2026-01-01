package dev.TrueFood.repositories;

import dev.TrueFood.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    """)
    Page<Advertisement> getAdverticementsWithPagination(@Param("name") String name, PageRequest pageRequest);


    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND a.categoryId = :categoryId
    """)
    Page<Advertisement> getAdverticementsByCategory(@Param("name") String name, @Param("categoryId") Long categoryId, PageRequest pageRequest);


    @Query("""
    SELECT a FROM Advertisement a
    WHERE (a.authorId = :id)
    """)
    Page<Advertisement> getAdverticementByUser(@Param("id") Long id, PageRequest pageRequest);



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