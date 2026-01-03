package dev.TrueFood.controllers;

import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.CategoryService;
import dev.TrueFood.services.OrderService;
import dev.TrueFood.services.UserService;
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
    private final OrderService orderService;
    private final CategoryService categoryService;

    @GetMapping("order/{order-type}/{page}/{size}") //todo разделить объявления и задачи
    public Page<Order> getAdvertisements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,
            @PathVariable(name = "order-type") String orderType,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return orderService.getOrders(name,orderType ,categoryId, pageRequest);
    }

    @GetMapping("advertisement/{id}")
    public Order getAdvertisementById(
            @PathVariable(name = "id") int id)
    {
        return orderService.getAdverticementById((long) id);
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