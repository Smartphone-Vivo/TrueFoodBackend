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
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final AdvertisementMapping advertisementMapping;
    private final UserRepository userRepository;

    public Page<AdvertisementDto> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return advertisementRepository.getAdverticementByUser(id, pageRequest).map(advertisementMapping::toDto); //todo перетащить в AdvertisementService
    }

    public Page<AdvertisementDto> getAdvertisements(String name, Long categoryId, PageRequest pageRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
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
        advertisement.setCategory(categoryRepository.findById(advertisementDto.getCategoryId()).orElseThrow(() -> new NotFoundException("category not found")));
        advertisementRepository.save(advertisement);
    }

    public AdvertisementDto getAdvertisementById(Long id){
        return advertisementRepository.findById(id).map(advertisementMapping::toDto).orElseThrow(() -> new NotFoundException("advertisement not found"));
    }

    public Page<AdvertisementDto> getFavouriteAdvertisements(Long id, PageRequest pageRequest) {
        return advertisementRepository.getFavouritesAdvertisements(id, pageRequest).map(advertisementMapping::toDto);
    }

    public void deleteFavouriteAdvertisement(Long id, Long advId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Advertisement advertisement = advertisementRepository.findById(advId).orElseThrow(() -> new NotFoundException("advertisement not found"));
        List<Advertisement> userFavourites = user.getFavourites();
        if(user.getFavourites().contains(advertisement)){
            userFavourites.remove(advertisement);
            user.setFavourites(userFavourites);
            userRepository.save(user);
        }
    }

    public void addToFavourites(Long id, Long advId){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Advertisement advertisement = advertisementRepository.findById(advId).orElseThrow(() -> new NotFoundException("advertisement not found"));

        List<Advertisement> userFavourites = user.getFavourites();

        if(userFavourites.contains(advertisement)){
            throw new RuntimeException("advertisement is already in favourite"); //todo сделать кастомное
        }
        else{
            userFavourites.add(advertisement);

            user.setFavourites(userFavourites);

            userRepository.save(user);
        }
    }

}
