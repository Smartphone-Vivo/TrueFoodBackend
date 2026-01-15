package dev.TrueFood.services;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.enums.Role;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.mapping.ImageMapping;
import dev.TrueFood.mapping.TaskMapping;
import dev.TrueFood.entity.*;
import dev.TrueFood.entity.User;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapping taskMapping;
    private final CategoryRepository categoryRepository;
    private final ImageMapping imageMapping;
    private final ImageRepository imageRepository;

    public Page<TaskDto> getTasks(String name, Long categoryId, PageRequest pageRequest) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        List<Long> children = category.getChildrenId();

        return taskRepository.getTasksByCategory(name, categoryId, children,  pageRequest).map(taskMapping::toDto);
    }

    public void addTask(TaskDto taskDto, Long id) {

        Image image = imageMapping.toEntity(taskDto.getImages());

        imageRepository.save(image);

        Task  task = taskMapping.toEntity(taskDto);

        task.setImages(image);

        Long taskId = taskDto.getCategoryId();

        //todo ???
        Category category = categoryRepository.findById(taskId).orElseThrow(() -> new NotFoundException("category not found"));
        task.setCategory(category);

        Long authorId = taskDto.getAuthorId();
        //todo ???
        User author = userRepository.findById(authorId).orElseThrow(() -> new NotFoundException("user not found"));
        task.setAuthor(author); //todo вот это возможно можно удалить

        taskRepository.save(task);
    }


    public void addTaskResponse(Long id, Long taskId) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));

        if(task.getAuthor().equals(user)) {
            throw new RuntimeException("самолайк отклонен");
        }

        if(task.getWorkers().contains(user)) {
            throw new RuntimeException("user already has worker");
        }
        else{
            task.getWorkers().add(user);
            taskRepository.save(task);
        }
    }

    public Page<TaskDto> getMyTasks(Long id, PageRequest pageRequest) {
        return taskRepository.getMyTask(id, pageRequest).map(taskMapping::toDto);
    }

    public Page<TaskDto> getMyResponses(Long id, PageRequest pageRequest) {
        return taskRepository.getMyResponses(id, pageRequest).map(taskMapping::toDto);
    }

    public void removeWorker(Long id, Long taskId, Long workerId) {
        User removeUser = userRepository.findById(workerId).orElseThrow(() -> new NotFoundException("user not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));

        if(id.equals(task.getAuthor().getId())) {
            List<User> workers = task.getWorkers();
            workers.remove(removeUser);
            task.setWorkers(workers);
            taskRepository.save(task);
        }

    }

    public void confirmWorker(Long id, Long taskId, Long workerId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));

        if (task.getAuthor().getId().equals(id)) {
            task.getWorkers().clear();
            User user = userRepository.findById(workerId).orElseThrow(() -> new NotFoundException("user not found"));
            task.setAcceptedWorker(user);
            taskRepository.save(task);
        }
    }

    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id).map(taskMapping::toDto).orElseThrow(() -> new NotFoundException("task not found"));
    }

    public void editTask(Long id, JwtAuthentication authentication, TaskDto taskDto) {

        boolean isAdmin = (authentication.getAuthorities().iterator().next()) == Role.ADMIN;

        if(Objects.equals(taskDto.getAuthorId(), id) || isAdmin) {

            Task changingTask = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new NotFoundException("task not found"));

            Task changedTask = taskMapping.updateTask(taskDto, changingTask);

            taskRepository.save(changedTask);

        }
    }

    public void deleteTask(Long id, JwtAuthentication authentication, Long taskId) {

        boolean isAdmin = (authentication.getAuthorities().iterator().next()) == Role.ADMIN;

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));

        if(task.getAuthor().getId().equals(id) || isAdmin) {
            taskRepository.delete(task);
        }
    }



}
