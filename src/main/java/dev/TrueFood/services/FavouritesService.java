package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.User;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.repositories.UserRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void addToFavourites(Long userId, Long advId){

        if(!(userRepository.existsById(userId)) || !(advertisementRepository.existsById(advId)) ){
            throw new NotFoundException("User or Advertisement not found");
        }

        if(userRepository.isAdvertisementIdFavourites(userId, advId)){
            userRepository.removeFromFavouritesNative(userId, advId);
        }
        else{
            userRepository.addToFavouritesNative(userId, advId);
        }

    }


}
