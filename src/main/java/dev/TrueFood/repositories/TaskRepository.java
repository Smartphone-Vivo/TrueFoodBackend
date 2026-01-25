package dev.TrueFood.repositories;

import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
    SELECT t FROM Task t
    JOIN FETCH t.category
    JOIN FETCH t.author
    JOIN FETCH t.acceptedWorker
    JOIN FETCH t.workers
    JOIN FETCH t.images
    WHERE (t.title LIKE CONCAT('%', :name,'%'))
    AND (t.category = :category OR t.category IN :childrenCategory )
    """)
    Page<Task> getTasksByCategory(
            @Param("name") String name,
            @Param("category") Category category,
            @Param("childrenCategory") List<Category> childrenCategory,
            PageRequest pageRequest);


    @Query("""
    SELECT t FROM Task t
    JOIN FETCH t.category
    JOIN FETCH t.author
    JOIN FETCH t.acceptedWorker
    JOIN FETCH t.workers
    JOIN FETCH t.images
    WHERE (t.author.id = :id)
    """)
    Page<Task> getMyTask(
            @Param("id") Long id,
            PageRequest pageRequest);

    @Query("""
    SELECT DISTINCT t FROM Task t
    JOIN FETCH t.category
    JOIN FETCH t.author
    JOIN FETCH t.images
    LEFT JOIN t.workers w
    LEFT JOIN t.acceptedWorker aw
    WHERE (w.id = :id  OR t.acceptedWorker.id = :id)
""")
    Page<Task> getMyResponses(@Param("id") Long id, PageRequest pageRequest);


    @Query("""
    SELECT t FROM Task t
    JOIN FETCH t.category
    JOIN FETCH t.author
    JOIN FETCH t.acceptedWorker
    JOIN FETCH t.workers
    JOIN FETCH t.images
    WHERE t.id = :id
    """)
    Optional<Task> findTaskById(@Param("id") Long id);

}
