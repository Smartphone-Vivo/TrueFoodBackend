package dev.TrueFood.services;

import dev.TrueFood.dto.OrderDto;
import dev.TrueFood.dto.mapping.OrderMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.Task;
import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AdvertisementRepository advertisementRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final OrderRepository orderRepository;
    private final OrderMapping orderMapping;

    public void addAdverticement(OrderDto orderDto, Long id) {

        List<String> imageUrls = orderDto.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        orderDto.setImagesId(image);

        Order order = orderMapping.toEntity(orderDto);

        order.setAuthorId(id);

        orderRepository.save(order);
    }

    public void addTask(OrderDto orderDto, Long id) {
        List<String> imageUrls = orderDto.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        orderDto.setImagesId(image);

//        order.setAuthorId(id);

        Order order = orderMapping.toEntity(orderDto);

        orderRepository.save(order);
    }

    public Page<Order> getOrders(String name, String orderTypeString, Long categoryId, PageRequest pageRequest) {

        OrderType orderType;

        try{
            orderType = OrderType.valueOf(orderTypeString.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new RuntimeException("");
        }

        if(categoryId == null) {
            return orderRepository.getOrdersWithPagination(name, orderType,  pageRequest);
        }
        else{
            return orderRepository.getOrdersByCategory(name, orderType, categoryId,  pageRequest);
        }
    }

    public Order getAdverticementById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("advertisement not found"));
    }

    public Page<Advertisement> getFavouriteAdvertisements(Long id, PageRequest pageRequest) {

        Page<Advertisement> response = advertisementRepository.getFavouritesAdvertisements(id, pageRequest);
        return response;
    }

    public void deleteFavoiriteAdvertisement(Long id, Long advId) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Order order = orderRepository.findById(advId).orElse(null);

        List<Order> userFavourites = user.getFavourites();

        if(user.getFavourites().contains(order)){
            userFavourites.remove(order);

            user.setFavourites(userFavourites);

            userRepository.save(user);
        }
    }

}
