package dev.TrueFood.controllers;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.services.CategoryService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/general")
@RequiredArgsConstructor
public class GeneralController {

    private final AdvertisementService advertisementService;
    private final CategoryService categoryService;

    @GetMapping("adverticement/{page}/{size}")
    public Page<Advertisement> getAdverticements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return advertisementService.getAdverticements(name, categoryId, pageRequest);
    }

    @GetMapping("advertisement/{id}")
    public Advertisement getAdverticementById(
            @PathVariable(name = "id") int id
    ){
        return advertisementService.getAdverticementById((long) id);
    }

    @PostMapping("adverticement")
    public void addAdverticement(@RequestBody Advertisement advertisement, JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        advertisementService.addAdverticement(advertisement, id);
    }

    @GetMapping("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }



}
