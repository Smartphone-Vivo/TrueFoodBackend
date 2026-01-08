package dev.TrueFood.services;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.dto.mapping.TaskMapping;
import dev.TrueFood.entity.*;
import dev.TrueFood.entity.users.User;
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

    private final ImageRepository imageRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapping taskMapping;
    private final CategoryRepository categoryRepository;

    public Page<TaskDto> getTasks(String name, Long categoryId, PageRequest pageRequest) {

        Category category = categoryRepository.findById(categoryId).orElse(null);
        List<Long> children = category.getChildrenId();

        return taskRepository.getTasksByCategory(name, categoryId, children,  pageRequest).map(taskMapping::toDto);
    }

    public void addTask(TaskDto taskDto, Long id) {
        List<String> imageUrls = taskDto.getImagesId().getImageUrls();

        Category category = categoryRepository.findById(taskDto.getCategoryId()).orElse(null);

        taskDto.setCategoryId(category.getId());

//        taskDto.setCategory(category);

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        taskDto.setImagesId(image);

        taskDto.setAuthorId(id);

        Task task = taskMapping.toEntity(taskDto);

        taskRepository.save(task);
    }


    public void addTaskResponse(Long id, Long taskId) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));

        if(task.getAuthorId().equals(user.getId())) {
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

    public void removeResponse(Long id, Long taskId, Long workerId) {
        User removeUser = userRepository.findById(workerId).orElseThrow(() -> new RuntimeException("user not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        if(id.equals(task.getAuthorId())){
            task.getWorkers().remove(removeUser);
            taskRepository.save(task); //todo переписать по человечески
        }

    }

    public void confirmWorker(Long id, Long taskId, Long workerId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));

        if (task.getAuthorId().equals(id)) {
            task.setWorkers(new ArrayList<User>());

            User user = userRepository.findById(workerId).orElseThrow(() -> new RuntimeException("user not found"));

            task.setAcceptedWorker(user);

            taskRepository.save(task);
        }
    }
}
