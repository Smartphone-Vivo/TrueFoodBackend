package dev.TrueFood.controllers;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.services.TaskService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("{page}/{size}")
    public Page<TaskDto> getTasks(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") Long categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return taskService.getTasks(name, categoryId, pageRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public void addTask(@RequestBody TaskDto taskDto, JwtAuthentication authentication){
        Long id = authentication.getUserId();
        taskService.addTask(taskDto, id);
    }

    @GetMapping("{id}")
    public TaskDto getTaskById(@PathVariable(name = "id") Long id){
        return taskService.getTaskById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("tasks-by-user/{id}/{page}/{size}")
    public Page<TaskDto> getTasksByUser(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size
    )
    {
        PageRequest pageRequest = PageUtils.createPageRequest(page, size, "id,asc");

        return taskService.getMyTasks(id, pageRequest);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("add-task-response/{task-id}")
    public void addTaskResponse(
            JwtAuthentication authentication,
            @PathVariable(name = "task-id") Long taskId

    ){
        Long id = authentication.getUserId();
        taskService.addTaskResponse(id, taskId);
    }

    @PreAuthorize("isAuthenticated()")
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

    @DeleteMapping("remove-worker/{taskId}/{workerId}")
    public void removeWorker(
            JwtAuthentication jwtAuthentication,
            @PathVariable(name = "workerId") Long workerId,
            @PathVariable(name = "taskId") Long taskId
    ){
        Long id = jwtAuthentication.getUserId();
        taskService.removeWorker(id, taskId, workerId);
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

    @PutMapping("edit-task")
    public void editTask(
            JwtAuthentication authentication,
            @RequestBody TaskDto taskDto)
    {
        Long id = authentication.getUserId();

        taskService.editTask(id, taskDto);
    }

    @DeleteMapping("delete-task/{taskId}")
    public void deleteTask(
            JwtAuthentication authentication,
            @PathVariable Long taskId
    ){
        Long id = authentication.getUserId();
        taskService.deleteTask(id, taskId);
    }

}
