package dev.TrueFood.services;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.repositories.AdverticementRepository;
import dev.TrueFood.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional //todo вот эту ебатню разгрести
public class AdverticementService {

    private final AdverticementRepository adverticementRepository;
    private final ImageRepository imageRepository;


    public List<Adverticement> getAllAdverticements() {
        return adverticementRepository.findAll();
    }

    public void addAdverticement(Adverticement adverticement, Long id) {

        List<String> imageUrls = adverticement.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        adverticement.setImagesId(image);

        adverticement.setAuthorId(id);

        adverticementRepository.save(adverticement);
    }

    public Page<Adverticement> getAdverticements(String name, Long categoryId, PageRequest pageRequest) {

        if(categoryId == null) {
            return adverticementRepository.getAdverticementsWithPagination(name,  pageRequest);
        }
        else{
            return adverticementRepository.getAdverticementsByCategory(name, categoryId,  pageRequest);
        }
    }

    public Adverticement getAdverticementById(Long id) {
        return adverticementRepository.findById(id).orElse(null);
    }


}
