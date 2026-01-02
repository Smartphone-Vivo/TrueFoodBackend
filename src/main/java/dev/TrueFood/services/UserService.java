package dev.TrueFood.services;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.repositories.ImageRepository;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;
    private final ImageRepository imageRepository;

    public User getMyProfile(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));
    }

    public Page<Advertisement> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return advertisementRepository.getAdverticementByUser(id, pageRequest);
    }

    public void addToFavourites(Long id, Long advId){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Advertisement advertisement = advertisementRepository.findById(advId).orElseThrow(() -> new RuntimeException("advertisement not found"));

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