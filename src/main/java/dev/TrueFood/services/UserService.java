package dev.TrueFood.services;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final AdvertisementRepository advertisementRepository;

    public User getMyProfile(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));
    }

    public Page<Advertisement> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return advertisementRepository.getAdverticementByUser(id, pageRequest);
    }

}
