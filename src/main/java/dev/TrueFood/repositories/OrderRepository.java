package dev.TrueFood.repositories;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.users.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
    SELECT a FROM Order a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND a.orderType = :orderType
    """)
    Page<Order> getOrdersWithPagination(@Param("name") String name, OrderType orderType, PageRequest pageRequest);


    @Query("""
    SELECT a FROM Order a
    WHERE (a.title LIKE CONCAT('%', :name,'%'))
    AND a.categoryId = :categoryId
    AND a.orderType = :orderType
    """)
    Page<Order> getOrdersByCategory(@Param("name") String name, @Param("orderType") OrderType orderType, @Param("categoryId") Long categoryId, PageRequest pageRequest);


}
