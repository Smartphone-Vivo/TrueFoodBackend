package dev.TrueFood.repositories;

import dev.TrueFood.AbstractDbConfig;
import dev.TrueFood.entity.Advertisement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class AdvertisementRepositoryTest extends AbstractDbConfig {
    //todo mockmvc abstract

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