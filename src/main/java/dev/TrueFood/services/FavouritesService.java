package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.User;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouritesService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapping advertisementMapping;
    private final UserRepository userRepository;

    public Page<AdvertisementDto> getFavouriteAdvertisements(Long id, PageRequest pageRequest) {
        return advertisementRepository.getFavouritesAdvertisements(id, pageRequest).map(advertisementMapping::toDto);
    }

    public void deleteFavouriteAdvertisement(Long id, Long advId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Advertisement advertisement = advertisementRepository.findById(advId).orElseThrow(() -> new NotFoundException("advertisement not found"));
        List<Advertisement> userFavourites = user.getFavourites();
        if(user.getFavourites().contains(advertisement)){
            userFavourites.remove(advertisement);
            user.setFavourites(userFavourites);
            userRepository.save(user);
        }
    }

    public void addToFavourites(Long id, Long advId){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Advertisement advertisement = advertisementRepository.findById(advId).orElseThrow(() -> new NotFoundException("advertisement not found"));

        List<Advertisement> userFavourites = user.getFavourites();

        if(userFavourites.contains(advertisement)){
            throw new RuntimeException("advertisement is already in favourite");
        }
        else{
            userFavourites.add(advertisement);

            user.setFavourites(userFavourites);

            userRepository.save(user);
        }
    }

}
