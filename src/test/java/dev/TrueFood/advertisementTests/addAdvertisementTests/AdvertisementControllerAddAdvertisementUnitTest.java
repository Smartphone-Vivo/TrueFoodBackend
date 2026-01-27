package dev.TrueFood.advertisementTests.addAdvertisementTests;

import dev.TrueFood.controllers.AdvertisementController;
import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.ImageDto;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.exceptions.PermissionDeniedException;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdvertisementControllerAddAdvertisementUnitTest {

    @Mock
    private AdvertisementService advertisementService;

    @InjectMocks
    private AdvertisementController advertisementController;

    private AdvertisementDto validAdvertisementDto;
    private JwtAuthentication auth;

    @BeforeEach
    void setUp() {
        ImageDto imageDto = new ImageDto(null, List.of("test1", "test2"));

        validAdvertisementDto = new AdvertisementDto();
        validAdvertisementDto.setTitle("title");
        validAdvertisementDto.setDescription("description");
        validAdvertisementDto.setPrice(100);
        validAdvertisementDto.setAuthorId(1L);
        validAdvertisementDto.setCategoryId(1L);
        validAdvertisementDto.setLocation("location");
        validAdvertisementDto.setImages(imageDto);

        auth = mock(JwtAuthentication.class);
        when(auth.getUserId()).thenReturn(1L);
    }

    @Test
    void addAdvertisement_ValidRequest_ShouldCallService() {
        // Arrange
        doNothing().when(advertisementService)
                .addAdvertisement(any(AdvertisementDto.class), eq(1L));

        // Act
        advertisementController.addAdvertisement(validAdvertisementDto, auth);

        // Assert
        verify(advertisementService).addAdvertisement(any(AdvertisementDto.class), eq(1L));
        verifyNoMoreInteractions(advertisementService);
    }

    @Test
    void addAdvertisement_ServiceThrowsPermissionDenied_ShouldPropagateException() {
        // Arrange
        doThrow(new PermissionDeniedException("Permission denied"))
                .when(advertisementService)
                .addAdvertisement(any(AdvertisementDto.class), eq(1L));

        // Act & Assert
        assertThrows(PermissionDeniedException.class, () -> {
            advertisementController.addAdvertisement(validAdvertisementDto, auth);
        });

        verify(advertisementService).addAdvertisement(any(AdvertisementDto.class), eq(1L));
    }

    @Test
    void addAdvertisement_ServiceThrowsNotFound_ShouldPropagateException() {
        // Arrange
        doThrow(new NotFoundException("Category not found"))
                .when(advertisementService)
                .addAdvertisement(any(AdvertisementDto.class), eq(1L));

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            advertisementController.addAdvertisement(validAdvertisementDto, auth);
        });

        verify(advertisementService).addAdvertisement(any(AdvertisementDto.class), eq(1L));
    }

}