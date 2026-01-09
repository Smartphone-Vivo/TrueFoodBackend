package dev.TrueFood.controllers;

import dev.TrueFood.dto.AdvertisementDto;
import dev.TrueFood.dto.CategoryDto;
import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.dto.UserDto;
import dev.TrueFood.services.*;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/guest")
@RequiredArgsConstructor
public class GuestController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final AdvertisementService advertisementService;
    private final TaskService taskService;

    @GetMapping("advertisements/{page}/{size}")
    public Page<AdvertisementDto> getAdvertisements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return advertisementService.getAdvertisements(name ,categoryId, pageRequest);
    }

    @GetMapping("tasks/{page}/{size}")
    public Page<TaskDto> getTasks(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return taskService.getTasks(name, categoryId, pageRequest);
    }

    @GetMapping("advertisement/{id}")
    public AdvertisementDto getAdvertisementById(
            @PathVariable(name = "id") Long id) //todo тут был int
    {
        return advertisementService.getAdvertisementById(id); //todo тут убрал (long)
    }

    @GetMapping("advertisements-by-user/{id}/{page}/{size}")
    public Page<AdvertisementDto> getAdvertisementsByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return userService.getAdvertisementsByUser(id, pageRequest);
    }


    @GetMapping("profile/{id}")
    public UserDto getProfile(@PathVariable Long id) {
        return userService.getProfile(id);
    }

    @GetMapping("categories")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }



}