package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.User;
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
    private final CategoryRepository categoryRepository;
    private final AdvertisementMapping advertisementMapping;


    public Page<AdvertisementDto> getAdvertisements(String name, Long categoryId, PageRequest pageRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        List<Long> children = category.getChildrenId();
        return advertisementRepository.getAdvertisementsByCategory(name, categoryId, children, pageRequest).map(advertisementMapping::toDto);
    }

    public AdvertisementDto getAdvertisementById(Long id){
        return advertisementRepository.findById(id).map(advertisementMapping::toDto).orElseThrow(() -> new NotFoundException("advertisement not found"));
    }

    public Page<AdvertisementDto> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return advertisementRepository.getAdverticementByUser(id, pageRequest).map(advertisementMapping::toDto);
    }

    public void addAdvertisement(AdvertisementDto advertisementDto, Long id) {
        Advertisement advertisement = advertisementMapping.toEntity(advertisementDto);
        advertisement.setAuthorId(id);
        advertisementRepository.save(advertisement);
    }


}
