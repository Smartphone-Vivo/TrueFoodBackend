package dev.TrueFood.repositories;

import dev.TrueFood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
    SELECT c FROM Category c
    join fetch c.parent
    WHERE c.id = :id
    """)
    Optional<Category> findCategoryById(@Param("id") Long id);
}
