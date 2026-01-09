package dev.TrueFood.mapping;

import dev.TrueFood.dto.TaskDto;
import dev.TrueFood.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapping {

    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

}
