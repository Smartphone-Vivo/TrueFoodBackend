package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.mapping.AdvertisementMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final AdvertisementMapping advertisementMapping;

    public Page<AdvertisementDto> getAdvertisements(String name, Long categoryId, PageRequest pageRequest) {


        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("category not found"));
        List<Long> children = category.getChildrenId();
        return advertisementRepository.getAdvertisementsByCategory(name, category, children, pageRequest).map(advertisementMapping::toDto);

    }

    public void addAdvertisement(AdvertisementDto advertisementDto, Long id) {

        Advertisement advertisement = advertisementMapping.toEntity(advertisementDto);

        List<String> imageUrls = advertisement.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        advertisement.setImagesId(image);

        advertisement.setAuthorId(id);

        advertisement.setCategory(categoryRepository.findById(advertisementDto.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found")));

        advertisementRepository.save(advertisement);
    }

    public AdvertisementDto getAdvertisementById(Long id){
        AdvertisementDto advertisement = advertisementRepository.findById(id).map(advertisementMapping::toDto).orElse(null);

        return advertisement;
    }

}
