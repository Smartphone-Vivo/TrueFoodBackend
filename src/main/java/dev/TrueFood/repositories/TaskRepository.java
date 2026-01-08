package dev.TrueFood.repositories;

import dev.TrueFood.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
    SELECT t FROM Task t
    JOIN FETCH t.imagesId
    LEFT JOIN FETCH t.workers w
    LEFT JOIN FETCH w.avatar a
    LEFT JOIN FETCH t.acceptedWorker aw
    LEFT JOIN FETCH aw.avatar
    WHERE (t.title LIKE CONCAT('%', :name,'%'))
    AND (t.categoryId = :categoryId OR t.categoryId IN :childrenCategory )
    """)
    Page<Task> getTasksByCategory(
            @Param("name") String name,
            @Param("categoryId") Long categoryId,
            @Param("childrenCategory") List<Long> childrenCategory, //todo выпилить
            PageRequest pageRequest);

    @Query("""
    SELECT t FROM Task t
    JOIN FETCH t.imagesId
    LEFT JOIN FETCH t.workers w
    LEFT JOIN FETCH w.avatar
    LEFT JOIN FETCH t.acceptedWorker aw
    LEFT JOIN FETCH aw.avatar
    WHERE (t.author.id = :id)
    """)
    Page<Task> getMyTask(
            @Param("id") Long id,
            PageRequest pageRequest);

    @Query("""
    SELECT t FROM Task t
    JOIN FETCH t.imagesId
    LEFT JOIN FETCH t.workers w
    LEFT JOIN FETCH w.avatar
    LEFT JOIN FETCH t.acceptedWorker aw
    LEFT JOIN FETCH aw.avatar
    WHERE (w.id = :id  OR aw.id = :id)
""")
    Page<Task> getMyResponses(@Param("id") Long id, PageRequest pageRequest);

}
