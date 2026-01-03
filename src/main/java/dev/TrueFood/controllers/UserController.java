package dev.TrueFood.controllers;

import dev.TrueFood.dto.OrderDto;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.Review;
import dev.TrueFood.entity.Task;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
import dev.TrueFood.services.OrderService;
import dev.TrueFood.services.TaskService;
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
    private final OrderService orderService;
    private final AdvertisementService advertisementService;
    private final TaskService taskService;

    @GetMapping("advertisements-by-user/{id}/{page}/{size}")
    public Page<Order> getAdvertisementsByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return userService.getAdvertisementsByUser(id, pageRequest);
    }

    @GetMapping("my-profile")
    public User getMyProfile(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return userService.getMyProfile(id);
    }

    @PostMapping("advertisement") //todo исправить
    public void addAdvertisement(@RequestBody Advertisement advertisement, JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        advertisementService.addAdvertisement(advertisement, id);
    }

    @PostMapping("task")
    public void addTask(@RequestBody Task task, JwtAuthentication authentication){
        Long id = authentication.getUserId();
        taskService.addTask(task, id);
    }

    @GetMapping("add-to-favourites/{advId}")
    public void addToFavourites(
            JwtAuthentication authentication,
            @PathVariable(name = "advId") Long advId
    ){
        Long id = authentication.getUserId();
        userService.addToFavourites(id, advId); //todo перетащить в AdvertisementService
    }

    @DeleteMapping("delete-favourite-advertisement/{advId}")
    public void deleteFavouriteAdvertisement(
            JwtAuthentication authentication,
            @PathVariable(name = "advId") Long advId
    ){
        Long id = authentication.getUserId();
        orderService.deleteFavoiriteAdvertisement(id, advId);
    }

    @GetMapping("get-favourite-advertisements/{page}/{size}")
    public Page<Advertisement> getAdvertisements(
            JwtAuthentication authentication,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        Long id = authentication.getUserId();

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return orderService.getFavouriteAdvertisements(id, pageRequest);
    }

    @PostMapping("add-review/{userId}")
    public void addReview(
            @PathVariable(name = "userId") Long userId,
            @RequestBody Review review,
            JwtAuthentication authentication){
        Long id = authentication.getUserId();
        userService.addReview(review, id, userId);
    }



}