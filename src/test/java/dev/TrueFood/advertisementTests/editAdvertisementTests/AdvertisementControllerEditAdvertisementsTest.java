package dev.TrueFood.advertisementTests.editAdvertisementTests;

import dev.TrueFood.controllers.AdvertisementController;
import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.enums.Role;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdvertisementControllerEditAdvertisementsTest {

    @Mock
    private AdvertisementService advertisementService;

    @InjectMocks
    private AdvertisementController advertisementController;

    private JwtAuthentication authentication;

    private AdvertisementDto advertisementDto;

    @BeforeEach
    void setUp() {
        authentication = new JwtAuthentication();
        authentication.setAuthenticated(true);
        authentication.setUserId(1L);
        authentication.setRoles(Collections.singleton(Role.USER));

        advertisementDto = new AdvertisementDto();

        advertisementDto.setTitle("testAdv");
        advertisementDto.setDescription("testDescr");
        advertisementDto.setLocation("testLoc");
        advertisementDto.setPrice(123);
    }

    @Test
    void editAdvertisement_whenUserIsAuthenticated_shouldCallService() {
        //when
        advertisementController.editAdvertisement(authentication, advertisementDto);

        //then
        then(advertisementService).should().editAdvertisement(authentication, advertisementDto);

    }

}