package dev.TrueFood.controllers;

import dev.TrueFood.dto.OrderDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.Task;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.OrderService;
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

    private final OrderService orderService;
    private final CategoryService categoryService;

    @GetMapping("order/{order-type}/{page}/{size}")
    public Page<Order> getAdverticements(
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
    public Order getAdverticementById(
            @PathVariable(name = "id") int id
    ){
        return orderService.getAdverticementById((long) id);
    }

    @PostMapping("adverticement")
    public void addAdverticement(@RequestBody OrderDto orderDto, JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        orderService.addAdverticement(orderDto, id);
    }

    @PostMapping("task")
    public void addTask(@RequestBody OrderDto OrderDto, JwtAuthentication authentication){
        Long id = authentication.getUserId();
        orderService.addTask(OrderDto, id);
    }

    @GetMapping("categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }



}
