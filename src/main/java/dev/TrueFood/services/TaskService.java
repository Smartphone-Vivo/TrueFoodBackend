package dev.TrueFood.services;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.enums.Role;
import dev.TrueFood.exceptions.NotFoundException;
import dev.TrueFood.exceptions.PermissionDeniedException;
import dev.TrueFood.exceptions.SelfLikeException;
import dev.TrueFood.exceptions.UserAlreadyWorkerException;
import dev.TrueFood.jwt.JwtAuthentication;
import dev.TrueFood.mapping.TaskMapping;
import dev.TrueFood.entity.*;
import dev.TrueFood.entity.User;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapping taskMapping;
    private final CategoryRepository categoryRepository;

    public Page<TaskDto> getTasks(String name, Long categoryId, PageRequest pageRequest) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        List<Category> children = category.getChildren();

        return taskRepository.getTasksByCategory(name, category, children,  pageRequest).map(taskMapping::toDto);
    }

    public TaskDto getTaskById(Long id) {
        return taskRepository.findTaskById(id).map(taskMapping::toDto).orElseThrow(() -> new NotFoundException("task not found"));
    }

    @Transactional
    public void addTask(TaskDto taskDto, Long id) {

        if (Objects.equals(taskDto.getAuthorId(), id)) {

            Task task = taskMapping.toEntity(taskDto);

            task.setAuthor(userRepository.getReferenceById(taskDto.getAuthorId()));

            task.setCategory(categoryRepository.getReferenceById(taskDto.getCategoryId()));

            task.setCreatedAt(LocalDateTime.now());

            taskRepository.save(task);
        }
        else{
            throw new PermissionDeniedException("You do not have permission to add an advertisement");
        }
    }

    @Transactional
    public void editTask(JwtAuthentication authentication, TaskDto taskDto) {

        Long id = authentication.getUserId();
        boolean isAdmin = (authentication.getAuthorities().iterator().next()) == Role.ADMIN;

        if(Objects.equals(taskDto.getAuthorId(), id) || isAdmin) {
            Task changingTask = taskRepository.findTaskById(taskDto.getId()).orElseThrow(() -> new NotFoundException("task not found"));
            Task changedTask = taskMapping.updateTask(taskDto, changingTask);
            taskRepository.save(changedTask);
        }
    }

    @Transactional
    public void deleteTask(Long id, JwtAuthentication authentication, Long taskId) {
        boolean isAdmin = (authentication.getAuthorities().iterator().next()) == Role.ADMIN;
        Task task = taskRepository.findTaskById(taskId).orElseThrow(() -> new NotFoundException("task not found"));
        if(task.getAuthor().getId().equals(id) || isAdmin) {
            taskRepository.delete(task);
        }
    }


    @Transactional
    public void addTaskResponse(Long id, Long taskId) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        Task task = taskRepository.findTaskById(taskId).orElseThrow(() -> new NotFoundException("task not found"));

        if(task.getAuthor().equals(user)) {
            throw new SelfLikeException("самолайк отклонен(");
        }

        if(task.getWorkers().contains(user)) {
            throw new UserAlreadyWorkerException("user is already worker");
        }
        else{
            task.getWorkers().add(user);
            taskRepository.save(task);
        }
    }

    @Transactional
    public Page<TaskDto> getMyTasks(Long id, PageRequest pageRequest) {
        return taskRepository.getMyTask(id, pageRequest).map(taskMapping::toDto);
    }

    @Transactional
    public Page<TaskDto> getMyResponses(Long id, PageRequest pageRequest) {
        return taskRepository.getMyResponses(id, pageRequest).map(taskMapping::toDto);
    }

    @Transactional
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

    @Transactional
    public void confirmWorker(Long id, Long taskId, Long workerId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));

        if (task.getAuthor().getId().equals(id)) {
            task.getWorkers().clear();
            User user = userRepository.findById(workerId).orElseThrow(() -> new NotFoundException("user not found"));
            task.setAcceptedWorker(user);
            taskRepository.save(task);
        }
    }


}
