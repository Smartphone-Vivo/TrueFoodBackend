package dev.TrueFood.services;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.repositories.AdverticementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdverticementService {

    private final AdverticementRepository adverticementRepository;


    public List<Adverticement> getAllAdverticements() {
        return adverticementRepository.findAll();
    }

    public void addAdverticement(Adverticement adverticement) {
        adverticementRepository.save(adverticement);
    }

    public Page<Adverticement> getAdverticementsWithPagination(String name, PageRequest pageRequest) {
        return adverticementRepository.getAdverticementsWithPagination(name, pageRequest);
    }


}
