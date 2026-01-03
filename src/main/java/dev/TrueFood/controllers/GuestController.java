package dev.TrueFood.controllers;

import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.Task;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.jwt.JwtAuthentication;
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

    @GetMapping("advertisements/{page}/{size}") //todo разделить объявления и задачи
    public Page<Advertisement> getAdvertisements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return advertisementService.getAdvertisements(name ,categoryId, pageRequest);
    }

    @GetMapping("tasks/{page}/{size}") //todo разделить объявления и задачи
    public Page<Task> getTasks(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return taskService.getTasks(name, categoryId, pageRequest);
    }

    @GetMapping("advertisement/{id}")
    public Advertisement getAdvertisementById(
            @PathVariable(name = "id") int id)
    {
        return advertisementService.getAdvertisementById((long) id);
    }

    @GetMapping("profile/{id}")
    public User getProfile(@PathVariable Long id) {
        return userService.getProfile(id);
    }

    @GetMapping("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

}