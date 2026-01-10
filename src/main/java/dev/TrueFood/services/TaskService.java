package dev.TrueFood.services;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.exceptions.NotFoundException;
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

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapping taskMapping;
    private final CategoryRepository categoryRepository;

    public Page<TaskDto> getTasks(String name, Long categoryId, PageRequest pageRequest) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        List<Long> children = category.getChildrenId();

        return taskRepository.getTasksByCategory(name, categoryId, children,  pageRequest).map(taskMapping::toDto);
    }

    public void addTask(TaskDto taskDto, Long id) {
        Task  task = taskMapping.toEntity(taskDto);
        task.setAuthorId(id);
        taskRepository.save(task);
    }


    public void addTaskResponse(Long id, Long taskId) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));

        if(task.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("самолайк отклонен"); //todo сделать кастомное
        }

        if(task.getWorkers().contains(user)) {
            throw new RuntimeException("user already has worker"); //todo сделать кастомное
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

        if(id.equals(task.getAuthorId())){
            List<User> workers = task.getWorkers();
            workers.remove(removeUser);
            task.setWorkers(workers);
            taskRepository.save(task); //todo переписать по человечески
        }

    }

    public void confirmWorker(Long id, Long taskId, Long workerId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));

        if (task.getAuthorId().equals(id)) {
            task.getWorkers().clear();
            User user = userRepository.findById(workerId).orElseThrow(() -> new NotFoundException("user not found"));
            task.setAcceptedWorker(user);
            taskRepository.save(task);
        }
    }
}
