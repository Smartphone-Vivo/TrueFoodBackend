package dev.TrueFood.Advertisement;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.User;
import dev.TrueFood.enums.Role;
import dev.TrueFood.exceptions.PermissionDeniedException;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.repositories.CategoryRepository;
import dev.TrueFood.repositories.UserRepository;
import dev.TrueFood.services.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdvertisementServiceAddAdvertisementTest {

    @Mock
    AdvertisementRepository advertisementRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    AdvertisementMapping advertisementMapping;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private AdvertisementService advertisementService;


    private User testUser;
    private Category testCategory;
    private Advertisement testAdvertisement;
    private Image testImage;
    private AdvertisementDto validAdvertisementDto;



    @BeforeEach
    void setUp() {

        testUser = new User();
        testUser.setId(1L);
        testUser.setEmail("test@mail.ru");
        testUser.setFio("testFio");
        testUser.setRole(Role.USER);
        testUser.setEnable(true);

        testCategory = new Category();
        testCategory.setId(1L);
        testCategory.setName("testCategory");

        testImage = new Image();
        testImage.setId(1L);
        testImage.setImageUrls(List.of("url1", "url2"));

        validAdvertisementDto = new AdvertisementDto();
        validAdvertisementDto.setTitle("title");
        validAdvertisementDto.setDescription("description");
        validAdvertisementDto.setPrice(100);
        validAdvertisementDto.setAuthorId(1L);
        validAdvertisementDto.setCategoryId(1L);
        validAdvertisementDto.setLocation("location");

    }

    @Test
    void addAdvertisement_whenUserDtoIdMatchUserId_ShouldCreateAndSaveNewAdvertisement() {

        //arrange

        Long userId = 1L;

        Advertisement newAdvertisement = new Advertisement();
        newAdvertisement.setTitle(validAdvertisementDto.getTitle());
        newAdvertisement.setDescription(validAdvertisementDto.getDescription());
        newAdvertisement.setPrice(validAdvertisementDto.getPrice());
        newAdvertisement.setLocation(validAdvertisementDto.getLocation());

        when(advertisementMapping.toEntity(validAdvertisementDto)).thenReturn(newAdvertisement);
        when(userRepository.getReferenceById(1L)).thenReturn(testUser);
        when(categoryRepository.getReferenceById(1L)).thenReturn(testCategory);
        when(advertisementRepository.save(any(Advertisement.class))).thenReturn(newAdvertisement);

        ArgumentCaptor<Advertisement> advertisementCaptor = ArgumentCaptor.forClass(Advertisement.class);

        //act

        advertisementService.addAdvertisement(validAdvertisementDto, userId);

        //assert

        verify(advertisementMapping).toEntity(validAdvertisementDto);
        verify(userRepository).getReferenceById(1L);
        verify(categoryRepository).getReferenceById(1L);
        verify(advertisementRepository).save(advertisementCaptor.capture());

        Advertisement savedAdvertisement = advertisementCaptor.getValue();
        assertThat(savedAdvertisement.getAuthor()).isEqualTo(testUser);
        assertThat(savedAdvertisement.getCategory()).isEqualTo(testCategory);
        assertThat(savedAdvertisement.getCreatedAt()).isNotNull();
        assertThat(savedAdvertisement.getPrice()).isEqualTo(newAdvertisement.getPrice());
    }


    @Test
    void addAdvertisement_whenUserDtoIdNotMatchUserId_ShouldReturnPermissionDeniedException() {

        //arrange

        Long userId = 2L;

        Advertisement newAdvertisement = new Advertisement();
        newAdvertisement.setTitle(validAdvertisementDto.getTitle());
        newAdvertisement.setDescription(validAdvertisementDto.getDescription());
        newAdvertisement.setPrice(validAdvertisementDto.getPrice());
        newAdvertisement.setLocation(validAdvertisementDto.getLocation());

        //act&assert

        assertThatThrownBy(() -> advertisementService.addAdvertisement(validAdvertisementDto, userId))
                .isInstanceOf(PermissionDeniedException.class);

        verify(userRepository, never()).getReferenceById(any());
        verify(categoryRepository, never()).getReferenceById(any());
        verify(advertisementRepository, never()).save(any(Advertisement.class));


    }
}