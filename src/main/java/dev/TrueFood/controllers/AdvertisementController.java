package dev.TrueFood.controllers;


import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("api/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @GetMapping("/{page}/{size}")
    public Page<AdvertisementDto> getAdvertisements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);
        return advertisementService.getAdvertisements(name ,categoryId, pageRequest);
    }

    @GetMapping("{id}")
    public AdvertisementDto getAdvertisementById(
            @PathVariable(name = "id") Long id)
    {
        return advertisementService.getAdvertisementById(id);
    }

    @GetMapping("advertisements-by-user/{id}/{page}/{size}")
    public Page<AdvertisementDto> getAdvertisementsByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");
        return advertisementService.getAdvertisementsByUser(id, pageRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("new-advertisement")
    public void addAdvertisement(@RequestBody AdvertisementDto advertisementDto, JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        advertisementService.addAdvertisement(advertisementDto, id);
    }



}
