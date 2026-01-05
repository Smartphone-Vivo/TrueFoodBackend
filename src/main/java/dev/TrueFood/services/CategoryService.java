package dev.TrueFood.services;

import dev.TrueFood.dto.CategoryDto;
import dev.TrueFood.dto.mapping.CategoryMapping;
import dev.TrueFood.entity.Category;
import dev.TrueFood.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapping categoryMapping;
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapping::toDto).collect(Collectors.toList());
    }
}