package dev.TrueFood.mapping;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapping {

    @Mapping(target = "categoryId", expression = "java(task.getCategory().getId())")
    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);





}
