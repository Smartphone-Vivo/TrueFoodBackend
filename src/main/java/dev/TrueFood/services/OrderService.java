package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.mapping.AdvertisementMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
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
    private final AdvertisementMapping advertisementMapping;

    public Page<AdvertisementDto> getFavouriteAdvertisements(Long id, PageRequest pageRequest) {

        return advertisementRepository.getFavouritesAdvertisements(id, pageRequest).map(advertisementMapping::toDto);
    }

    public void deleteFavoiriteAdvertisement(Long id, Long advId) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Order order = orderRepository.findById(advId).orElse(null);

        List<Advertisement> userFavourites = user.getFavourites();

        if(user.getFavourites().contains(order)){
            userFavourites.remove(order);

            user.setFavourites(userFavourites);

            userRepository.save(user);
        }
    }

}
