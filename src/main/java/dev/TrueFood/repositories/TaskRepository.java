package dev.TrueFood.repositories;

import dev.TrueFood.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
    SELECT t FROM Task t
    WHERE (t.title LIKE CONCAT('%', :name,'%'))
    """)
    Page<Task> getTasksWithPagination(@Param("name") String name, PageRequest pageRequest);

    @Query("""
    SELECT t FROM Task t
    WHERE (t.title LIKE CONCAT('%', :name,'%'))
    AND t.categoryId = :categoryId
    """)
    Page<Task> getTasksByCategory(@Param("name") String name, @Param("categoryId") Long categoryId, PageRequest pageRequest);


}
