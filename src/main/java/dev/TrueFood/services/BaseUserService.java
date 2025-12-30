package dev.TrueFood.services;

import dev.TrueFood.entity.users.BaseUser;
import dev.TrueFood.repositories.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseUserService {

    private final BaseUserRepository baseUserRepository;

    public Optional<BaseUser> getByEmail(String email) {
        return baseUserRepository.findByEmail(email);
    }

}
