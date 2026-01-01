package dev.TrueFood.services;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional //todo вот эту ебатню разгрести
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final ImageRepository imageRepository;

    public void addAdverticement(Advertisement advertisement, Long id) {

        List<String> imageUrls = advertisement.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        advertisement.setImagesId(image);

        advertisement.setAuthorId(id);

        advertisementRepository.save(advertisement);
    }

    public Page<Advertisement> getAdverticements(String name, Long categoryId, PageRequest pageRequest) {

        if(categoryId == null) {
            return advertisementRepository.getAdverticementsWithPagination(name,  pageRequest);
        }
        else{
            return advertisementRepository.getAdverticementsByCategory(name, categoryId,  pageRequest);
        }
    }

    public Advertisement getAdverticementById(Long id) {
        return advertisementRepository.findById(id).orElse(null);
    }


}
