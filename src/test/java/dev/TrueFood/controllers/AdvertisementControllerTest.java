package dev.TrueFood.controllers;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.ImageDto;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.utils.PageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertisementControllerTest {

    @Mock
    AdvertisementService advertisementService;

    @InjectMocks
    AdvertisementController advertisementController;

    @BeforeEach
    void setUp() {
        AdvertisementDto validAdvertisementDto = new AdvertisementDto();

        ImageDto validImageDto = new ImageDto(null, List.of("image1", "image2"));

        validAdvertisementDto.setTitle("title");
        validAdvertisementDto.setDescription("description");
        validAdvertisementDto.setPrice(100);
        validAdvertisementDto.setAuthorId(1L);
        validAdvertisementDto.setCategoryId(1L);
        validAdvertisementDto.setLocation("location");
        validAdvertisementDto.setImages(validImageDto);

        PageImpl mockPage = new PageImpl<>(
                List.of(validAdvertisementDto),
                PageRequest.of(0, 12),
                1
        );
    }

    @Test
    void getAdvertisements_whenDtoValid_shouldReturnAdvertisements() {
//        //given
//
//        var name = "";
//        var categoryId = 0L;
//        var page = 1;
//        var size = 12;
//        var sort = "id,asc";
//
//        //when
//
//        when(advertisementService.getAdvertisements(
//                eq(name),
//                eq(categoryId),
//                any(Pageable.class)
//        )).thenReturn(mockPage);
//        //then


    }
}