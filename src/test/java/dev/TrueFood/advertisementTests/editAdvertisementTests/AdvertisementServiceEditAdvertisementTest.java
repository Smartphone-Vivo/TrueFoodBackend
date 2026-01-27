package dev.TrueFood.advertisementTests.editAdvertisementTests;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.enums.Role;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.exceptions.PermissionDeniedException;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.mapping.AdvertisementMapping;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.services.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AdvertisementServiceEditAdvertisementTest {

    @Mock
    AdvertisementRepository advertisementRepository;

    @Mock
    AdvertisementMapping advertisementMapping;

    @InjectMocks
    private AdvertisementService advertisementService;

    private AdvertisementDto advertisementDto;
    private Advertisement changingAdvertisement;
    private Advertisement changedAdvertisement;
    private JwtAuthentication userAuthentication;
    private JwtAuthentication adminAuthentication;

    private final Long userId = 1L;
    private final Long otherUserId = 2L;
    private final Long adminId = 99L;

    @BeforeEach
    void setUp() {

        advertisementDto = new AdvertisementDto();
        advertisementDto.setAuthorId(userId);
        advertisementDto.setId(1L);

        changingAdvertisement = new Advertisement();
        changedAdvertisement = new Advertisement();

        userAuthentication = new JwtAuthentication();
        userAuthentication.setUserId(userId);
        userAuthentication.setRoles(Set.of(Role.USER));
        userAuthentication.setAuthenticated(true);

        adminAuthentication = new JwtAuthentication();
        adminAuthentication.setUserId(adminId);
        adminAuthentication.setRoles(Set.of(Role.ADMIN));
        adminAuthentication.setAuthenticated(true);
    }

    //если авторid и id из authentication совпадают - должно вызвать сейв +
    //если авторid и id из authentication не совпадают - должно кинуть PermissionDeniedException +
    //если админ пытается редактировать - должно вызвать сейв +
    //если редактирует несуществующее объявление - вернет NotFoundException+

    //positive

    @Test
    void editAdvertisement_whenAdminTryEditAdvertisement_shouldCallSave() {
        //arrange

        when(advertisementRepository.findAdvertisementById(1L))
                .thenReturn(Optional.of(changingAdvertisement));

        when(advertisementMapping.updateAdvertisement(advertisementDto, changingAdvertisement))
                .thenReturn(changedAdvertisement);

        //act
        advertisementService.editAdvertisement(adminAuthentication, advertisementDto);

        //assert

        verify(advertisementRepository).findAdvertisementById(advertisementDto.getId());
        verify(advertisementMapping).updateAdvertisement(advertisementDto, changingAdvertisement);
        verify(advertisementRepository).save(changedAdvertisement);
    }

    @Test
    void editAdvertisement_whenAuthorIdMatchesWithAuthenticationId_shouldCallSave() {
        //arrange

        when(advertisementRepository.findAdvertisementById(1L))
                .thenReturn(Optional.of(changingAdvertisement));

        when(advertisementMapping.updateAdvertisement(advertisementDto, changingAdvertisement))
                .thenReturn(changedAdvertisement);

        //act

        advertisementService.editAdvertisement(userAuthentication, advertisementDto);

        //assert

        verify(advertisementRepository).findAdvertisementById(advertisementDto.getId());
        verify(advertisementMapping).updateAdvertisement(advertisementDto, changingAdvertisement);
        verify(advertisementRepository).save(changedAdvertisement);
    }

    //negative

    @Test
    void editAdvertisement_whenAuthorIdIsNotMatchesWithAuthenticationId_shouldCallPermissionDeniedException() {
        //arrange
        advertisementDto.setAuthorId(otherUserId);

        //assert
        assertThrows(PermissionDeniedException.class, () -> {
            advertisementService.editAdvertisement(userAuthentication, advertisementDto);
        });
    }

    @Test
    void editAdvertisement_whenUserTryEditAdvertisementThatNotExists_shouldReturnNotFountException() {
        //arrange
        advertisementDto.setId(999L);

        //assert
        assertThrows(NotFoundException.class, () -> {
            advertisementService.editAdvertisement(userAuthentication, advertisementDto);
        });

    }


}