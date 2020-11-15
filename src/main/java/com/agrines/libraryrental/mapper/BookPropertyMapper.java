package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.BookPropertyDto;
import com.agrines.libraryrental.entity.BookPropertyEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookPropertyMapper {

    public BookPropertyDto createDto(BookPropertyEntity property) {
        return BookPropertyDto.builder()
                .id(property.getId())
                .key(property.getKey())
                .value(property.getValue())
                .build();
    }

    public List<BookPropertyDto> createDto(List<BookPropertyEntity> properties) {
        if (CollectionUtils.isEmpty(properties)) {
            return Collections.emptyList();
        }
        return properties.stream()
                .map(this::createDto)
                .collect(Collectors.toList());
    }
}
