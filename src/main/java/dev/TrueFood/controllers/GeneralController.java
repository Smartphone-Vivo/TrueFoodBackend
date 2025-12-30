package dev.TrueFood.controllers;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.services.AdverticementService;
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

    private final AdverticementService adverticementService;
    private final CategoryService categoryService;

    @GetMapping("adverticement/{page}/{size}")
    public Page<Adverticement> getAdverticements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return adverticementService.getAdverticements(name, categoryId, pageRequest);
    }

    @GetMapping("advertisement/{id}")
    public Adverticement getAdverticementById(
            @PathVariable(name = "id") int id
    ){
        return adverticementService.getAdverticementById((long) id);
    }

    @PostMapping("adverticement")
    public void addAdverticement(@RequestBody Adverticement adverticement) {
        adverticementService.addAdverticement(adverticement);
    }

    @GetMapping("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("zalupa")
    public String getZalupa() {
        return "zalupa";
    }

}
