package dev.TrueFood.repositories;

import dev.TrueFood.entity.Adverticement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdverticementRepository extends JpaRepository<Adverticement, Long> {

    @Query("""
    SELECT a FROM Adverticement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    """)
    Page<Adverticement> getAdverticementsWithPagination(@Param("name") String name, PageRequest pageRequest);


    @Query("""
    SELECT a FROM Adverticement a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND a.categoryId = :categoryId
    """)
    Page<Adverticement> getAdverticementsByCategory(@Param("name") String name, @Param("categoryId") Long categoryId, PageRequest pageRequest);

}