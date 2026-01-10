package dev.TrueFood.controllers;

import dev.TrueFood.dto.CategoryDto;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.services.CategoryService;
import dev.TrueFood.services.TaskService;
import dev.TrueFood.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }



}
