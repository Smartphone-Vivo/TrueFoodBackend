package dev.TrueFood.controllers;

import dev.TrueFood.dto.*;
import dev.TrueFood.dto.mapping.AdvertisementMapping;
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
    private final AdvertisementMapping advertisementMapping;

    @GetMapping("advertisements-by-user/{id}/{page}/{size}")
    public Page<AdvertisementDto> getAdvertisementsByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return userService.getAdvertisementsByUser(id, pageRequest);
    }

    @GetMapping("my-profile")
    public UserDto getMyProfile(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return userService.getMyProfile(id);
    }

    @PostMapping("advertisement") //todo исправить
    public void addAdvertisement(@RequestBody AdvertisementDto advertisementDto, JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        advertisementService.addAdvertisement(advertisementDto, id);
    }

    @PostMapping("task")
    public void addTask(@RequestBody TaskDto taskDto, JwtAuthentication authentication){
        Long id = authentication.getUserId();
        taskService.addTask(taskDto, id);
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
    public Page<AdvertisementDto> getFavouriteAdvertisements(
            JwtAuthentication authentication,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        Long id = authentication.getUserId();

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return orderService.getFavouriteAdvertisements(id, pageRequest); //todo перенести в advertisementService
    }

    @PostMapping("add-review/{userId}")
    public void addReview(
            @PathVariable(name = "userId") Long userId,
            @RequestBody Review review,
            JwtAuthentication authentication){
        Long id = authentication.getUserId();
        userService.addReview(review, id, userId);
    }

    @GetMapping("add-task-response/{task-id}")
    public void addTaskResponse(
            JwtAuthentication authentication,
            @PathVariable(name = "task-id") Long taskId

            ){
        Long id = authentication.getUserId();

        taskService.addTaskResponse(id, taskId);

    }


}