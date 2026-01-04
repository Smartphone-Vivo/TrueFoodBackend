package dev.TrueFood.services;

import dev.TrueFood.dto.OrderDto;
import dev.TrueFood.dto.mapping.OrderMapping;
import dev.TrueFood.entity.Advertisement;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.Order;
import dev.TrueFood.entity.Task;
import dev.TrueFood.entity.users.User;
import dev.TrueFood.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ImageRepository imageRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public Page<Task> getTasks(String name, Long categoryId, PageRequest pageRequest) {

        if(categoryId == null) {
            return taskRepository.getTasksWithPagination(name, pageRequest);
        }
        else{
            return taskRepository.getTasksByCategory(name, categoryId,  pageRequest);
        }
    }

    public void addTask(Task task, Long id) {
        List<String> imageUrls = task.getImagesId().getImageUrls();

        Image image = new Image(null, imageUrls);

        imageRepository.save(image);

        task.setImagesId(image);

        task.setAuthorId(id);

        taskRepository.save(task);
    }


    public Task addTaskResponse(Long id, Long taskId){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));

        task.getWorkers().add(user);
        taskRepository.save(task);

        return task;

    }

}
