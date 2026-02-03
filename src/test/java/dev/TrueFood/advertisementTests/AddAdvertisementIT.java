package dev.TrueFood.advertisementTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.TrueFood.AbstractDbConfig;
import dev.TrueFood.TrueFoodApplication;
import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.ImageDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.enums.Role;
import dev.TrueFood.exceptions.PermissionDeniedException;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.repositories.AdvertisementRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TrueFoodApplication.class)
@AutoConfigureMockMvc
@Transactional
@Testcontainers
public class AddAdvertisementIT extends AbstractDbConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @BeforeEach
    public void setup() {
        JwtAuthentication authentication = new JwtAuthentication();
        authentication.setAuthenticated(true);
        authentication.setUserId(2L);
        authentication.setRoles(Set.of(Role.USER));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @SneakyThrows
    public void addAdvertisementValidRequest_ShouldSaveNewAdvertisement() {
        AdvertisementDto validAdvertisementDto = new AdvertisementDto();
        validAdvertisementDto.setTitle("Новое тестовое объявление");
        validAdvertisementDto.setDescription("Описание нового тестового объявления");
        validAdvertisementDto.setCategoryId(3L);
        validAdvertisementDto.setPrice(1500);
        validAdvertisementDto.setLocation("Санкт-Петербург");
        validAdvertisementDto.setAuthorId(2L);
        validAdvertisementDto.setEnable(true);

        ImageDto validImageDto = new ImageDto(null, List.of());
        validAdvertisementDto.setImages(validImageDto);

        mockMvc.perform(post("/api/advertisements/new-advertisement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validAdvertisementDto)))
                .andExpect(status().isOk());

        Advertisement addedAdvertisement = advertisementRepository.findByTitle(validAdvertisementDto.getTitle());

        assertThat(addedAdvertisement).isNotNull();
        assertThat(addedAdvertisement.getTitle()).isEqualTo("Новое тестовое объявление");
        assertThat(addedAdvertisement.getDescription()).isEqualTo("Описание нового тестового объявления");
        assertThat(addedAdvertisement.getPrice()).isEqualTo(1500);
        assertThat(addedAdvertisement.getLocation()).isEqualTo("Санкт-Петербург");
        assertThat(addedAdvertisement.isEnable()).isTrue();

        assertThat(addedAdvertisement.getAuthor()).isNotNull();
        assertThat(addedAdvertisement.getAuthor().getId()).isEqualTo(2L);

    }

    @Test
    @SneakyThrows
    public void testAddAdvertisementWithInvalidRequest_ShouldThrowPermissionDeniedException() {

        AdvertisementDto validAdvertisementDto = new AdvertisementDto();
        validAdvertisementDto.setTitle("Еще одно тестовое объявление");
        validAdvertisementDto.setDescription("Описание от другого пользователя");
        validAdvertisementDto.setCategoryId(4L);
        validAdvertisementDto.setPrice(800);
        validAdvertisementDto.setLocation("Казань");
        validAdvertisementDto.setAuthorId(3L);

        ImageDto validImageDto = new ImageDto(null, List.of());
        validAdvertisementDto.setImages(validImageDto);

        mockMvc.perform(post("/api/advertisements/new-advertisement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validAdvertisementDto)))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> {
                    assertThat(result.getResolvedException())
                            .isInstanceOf(PermissionDeniedException.class);
                });

        Advertisement addedAdvertisement = advertisementRepository.findByTitle(validAdvertisementDto.getTitle());

        assertThat(addedAdvertisement).isNull();


    }
}