package dev.TrueFood.mapping;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapping {

    @Mapping(target = "categoryId", expression = "java(task.getCategory().getId())")
    @Mapping(target = "authorId", expression = "java(task.getAuthor().getId())")
    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);





}
