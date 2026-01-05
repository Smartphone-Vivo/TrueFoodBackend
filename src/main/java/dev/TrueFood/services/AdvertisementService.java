package dev.TrueFood.services;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
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
    private final CategoryRepository categoryRepository;

    public Page<Advertisement> getAdvertisements(String name, Long categoryId, PageRequest pageRequest) {

        if(categoryId == null) {
            return advertisementRepository.getAdvertisementsWithPagination(name, pageRequest);
        }
        else{
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("category not found"));
            List<Long> children = category.getChildrenId();
            return advertisementRepository.getAdvertisementsByCategory(name, category, children, pageRequest);
        }
    }

    public void addAdvertisement(Advertisement advertisement, Long id) {
        List<String> imageUrls = advertisement.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        advertisement.setImagesId(image);

        advertisement.setAuthorId(id);

        advertisement.setCategory(categoryRepository.findById(advertisement.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found")));

        advertisementRepository.save(advertisement);
    }

    public Advertisement getAdvertisementById(Long id){
        return advertisementRepository.findById(id).orElseThrow(() -> new RuntimeException("advertisement not found"));
    }

}
