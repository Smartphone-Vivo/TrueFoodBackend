package dev.TrueFood.controllers;


import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.services.FavouritesService;
import dev.TrueFood.services.TaskService;
import dev.TrueFood.services.UserService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("isAuthenticated()")
@CrossOrigin
@RestController
@RequestMapping("api/favourites")
@RequiredArgsConstructor
public class FavouritesController {

    private final FavouritesService favouritesService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("get-favourite-advertisements/{page}/{size}")
    public Page<AdvertisementDto> getFavouriteAdvertisements(
            JwtAuthentication authentication,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        Long id = authentication.getUserId();

        return favouritesService.getFavouriteAdvertisements(id, PageUtils.createPageRequest(page, size, "id,asc"));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("add-to-favourites/{advId}")
    public void addToFavourites(
            JwtAuthentication authentication,
            @PathVariable(name = "advId") Long advId
    ){
        Long id = authentication.getUserId();
        favouritesService.addToFavourites(id, advId);
    }
    
}
