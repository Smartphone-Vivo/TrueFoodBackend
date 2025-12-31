package dev.TrueFood.services;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.AdverticementRepository;
import dev.TrueFood.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final AdverticementRepository adverticementRepository;

    public User getMyProfile(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("student not found"));
    }

    public Page<Adverticement> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return adverticementRepository.getAdverticementByUser(id, pageRequest);
    }

}
