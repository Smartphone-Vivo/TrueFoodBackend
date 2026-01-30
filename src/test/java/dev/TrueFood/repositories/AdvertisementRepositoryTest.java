package dev.TrueFood.repositories;

import dev.TrueFood.entity.Advertisement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class AdvertisementRepositoryTest {
    //todo mockmvc abstract
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Test
    void findAdvertisementById_shouldReturnNotEmpty() {
        var result = advertisementRepository.findAdvertisementById(1L);
        assertThat(result).isNotEmpty();
    }

    @Test
    void findAdvertisementById_shouldReturnEmptyWhenNotFound() {

        Optional<Advertisement> result = advertisementRepository.findAdvertisementById(999L);

        assertThat(result).isEmpty();
    }
}