package dev.TrueFood.controllers;

import dev.TrueFood.dto.*;
import dev.TrueFood.entity.Review;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.AdvertisementService;
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
    private final AdvertisementService advertisementService;
    private final TaskService taskService;

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
        advertisementService.deleteFavouriteAdvertisement(id, advId);
    }

    @GetMapping("get-favourite-advertisements/{page}/{size}")
    public Page<AdvertisementDto> getFavouriteAdvertisements(
            JwtAuthentication authentication,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        Long id = authentication.getUserId();

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return advertisementService.getFavouriteAdvertisements(id, pageRequest); //todo перенести в advertisementService
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

    @GetMapping("tasks-by-user/{id}/{page}/{size}") //todo уаааааа в юзер контроллер перенести жесть
    public Page<TaskDto> getTasksByUser(
            @PathVariable(name = "id") Long id, //todo сделать id через jwt
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    )
    {
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return taskService.getMyTasks(id, pageRequest);
    }

    @GetMapping("responses-by-user/{page}/{size}")
    public Page<TaskDto> getResponsesByUser(
            JwtAuthentication authentication,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    ){
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        Long id = authentication.getUserId();

        return taskService.getMyResponses(id, pageRequest);
    }

    @DeleteMapping("remove-response/{taskId}/{workerId}")
    public void removeResponse(
            JwtAuthentication jwtAuthentication,
            @PathVariable(name = "workerId") Long workerId,
            @PathVariable(name = "taskId") Long taskId
    ){
        Long id = jwtAuthentication.getUserId();
        taskService.removeResponse(id, taskId, workerId);
    }

    @GetMapping("confirm-worker/{taskId}/{workerId}")
    public void confirmWorker(
            JwtAuthentication jwtAuthentication,
            @PathVariable Long taskId,
            @PathVariable Long workerId
    ){
        Long id = jwtAuthentication.getUserId();
        taskService.confirmWorker(id, taskId, workerId);
    }



}