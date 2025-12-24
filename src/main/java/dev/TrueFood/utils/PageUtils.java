package dev.TrueFood.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageUtils {

    public static PageRequest createPageRequest(int page, int size, String sort){

        String[] parts = sort.split(",");
        String field = parts[0];
        Sort.Direction direction = Sort.Direction.fromString(parts[1].toUpperCase());

        return PageRequest.of(page, size, Sort.by(direction, field));

    }
}
