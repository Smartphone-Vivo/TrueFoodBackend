package dev.TrueFood.services;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.enums.Role;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.exceptions.PermissionDeniedException;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final AdvertisementMapping advertisementMapping;
    private final UserRepository userRepository;


    public Page<AdvertisementDto> getAdvertisements(String name, Long categoryId, PageRequest pageRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        List<Category> children = category.getChildren();

        return advertisementRepository.getAdvertisementsByCategory(name, category, children, pageRequest).map(advertisementMapping::toDto);
    }

    public AdvertisementDto getAdvertisementById(Long id){
        Advertisement advertisement = advertisementRepository.findAdvertisementById(id);

        AdvertisementDto advertisementDto = advertisementMapping.toDto(advertisement);

        advertisementDto.setAuthorId(advertisement.getAuthor().getId());

        advertisementDto.setCategoryId(advertisement.getCategory().getId());

        return advertisementDto;
    }

    public Page<AdvertisementDto> getAdvertisementsByUser(Long id, PageRequest pageRequest){
        return advertisementRepository.getAdverticementByUser(id, pageRequest).map(advertisementMapping::toDto);
    }

    @Transactional
    public void addAdvertisement(AdvertisementDto advertisementDto, Long id) {
        if(Objects.equals(advertisementDto.getAuthorId(), id)){

            Advertisement advertisement = advertisementMapping.toEntity(advertisementDto);

            advertisement.setAuthor(userRepository.getReferenceById(advertisementDto.getAuthorId()));

            advertisement.setCategory(categoryRepository.getReferenceById(advertisementDto.getCategoryId()));

            advertisement.setCreatedAt(LocalDateTime.now());

            advertisementRepository.save(advertisement);
        }
        else{
            throw new PermissionDeniedException("You do not have permission to add an advertisement");
        }

    }

    @Transactional
    public void editAdvertisement(JwtAuthentication authentication, AdvertisementDto advertisementDto) {

        Long id = authentication.getUserId();

        boolean isAdmin = (authentication.getAuthorities().iterator().next()) == Role.ADMIN;

        if(Objects.equals(advertisementDto.getAuthorId(), id) || isAdmin){
            Advertisement changingAdvertisement = advertisementRepository.findById(advertisementDto.getId()).orElseThrow(() -> new NotFoundException("advertisement not found"));

            Advertisement changedAdvertisement = advertisementMapping.updateAdvertisement(advertisementDto, changingAdvertisement);

            advertisementRepository.save(changedAdvertisement);
        }

    }

    @Transactional
    public void deleteAdvertisement(Long id, JwtAuthentication authentication, Long advertisementId) {

        Advertisement advertisement = advertisementRepository.findById(advertisementId).orElseThrow(() -> new NotFoundException("advertisement not found"));

        boolean isAdmin = (authentication.getAuthorities().iterator().next()) == Role.ADMIN;

        if(advertisement.getAuthor().getId().equals(id) || isAdmin){
            advertisementRepository.delete(advertisement);
        }
    }




}