package dev.TrueFood.dto.mapping;

import dev.TrueFood.dto.OrderDto;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.users.OrderType;
import org.springframework.stereotype.Component;

@Component
public class OrderMapping {

    public Order toEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setTitle(orderDto.getTitle());
        order.setDescription(orderDto.getDescription());
        order.setCategoryId(orderDto.getCategoryId());
        order.setPrice(orderDto.getPrice());
        order.setImagesId(orderDto.getImagesId());
        order.setLocation(orderDto.getLocation());
        order.setAuthorId(orderDto.getAuthorId());

        OrderType orderType = OrderType.valueOf(orderDto.getOrderType().toUpperCase());
        order.setOrderType(orderType);

        order.setCreatedAt(orderDto.getCreatedAt());
        order.setEnable(orderDto.isEnable());

        return order;
    }

}
