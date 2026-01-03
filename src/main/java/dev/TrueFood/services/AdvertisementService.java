package dev.TrueFood.services;

import dev.TrueFood.dto.mapping.OrderMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.users.OrderType;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final ImageRepository imageRepository;

    public Page<Advertisement> getAdvertisements(String name, Long categoryId, PageRequest pageRequest) {

        if(categoryId == null) {
            return advertisementRepository.getAdvertisementsWithPagination(name, pageRequest);
        }
        else{
            return advertisementRepository.getAdvertisementsByCategory(name, categoryId,  pageRequest);
        }
    }

    public void addAdvertisement(Advertisement advertisement, Long id) {
        List<String> imageUrls = advertisement.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        advertisement.setImagesId(image);

        advertisement.setAuthorId(id);

        advertisementRepository.save(advertisement);
    }

    public Advertisement getAdvertisementById(Long id){
        return advertisementRepository.findById(id).orElseThrow(() -> new RuntimeException("advertisement not found"));
    }

}
