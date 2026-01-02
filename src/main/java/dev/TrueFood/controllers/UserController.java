package dev.TrueFood.controllers;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.repositories.AdvertisementRepository;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.services.UserService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AdvertisementService advertisementService;

    @GetMapping("my-profile")
    public User getMyProfile(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return userService.getMyProfile(id);
    }

    @GetMapping("advertisements-by-user/{id}/{page}/{size}")
    public Page<Advertisement> getAdvertisementsByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size

    ){

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return userService.getAdvertisementsByUser(id, pageRequest);
    }

    @GetMapping("add-to-favourites/{advId}")
    public void addToFavourites(
            JwtAuthentication authentication,
            @PathVariable(name = "advId") Long advId
    ){
        Long id = authentication.getUserId();
        userService.addToFavourites(id, advId); //todo перетащить в AdvertisementService
    }

    @DeleteMapping("delete-favoirite-advertisement/{advId}")
    public void deleteFavoiriteAdvertisement(
            JwtAuthentication authentication,
            @PathVariable(name = "advId") Long advId
    ){
        Long id = authentication.getUserId();
        advertisementService.deleteFavoiriteAdvertisement(id, advId);

    }

    @GetMapping("get-favourite-advertisements/{page}/{size}")
    public Page<Advertisement> getAdvertisements(
            JwtAuthentication authentication,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        Long id = authentication.getUserId();

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return advertisementService.getFavouriteAdvertisements(id, pageRequest);
    }



}
