package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.RentalBookDto;
import com.agrines.libraryrental.entity.RentalBookEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentalBookMapper {

    public RentalBookDto createDto(RentalBookEntity rentalBooks) {
        return RentalBookDto.builder()
                .id(rentalBooks.getId())
                .status(rentalBooks.getStatus())
                .build();
    }

    public List<RentalBookDto> createDto(List<RentalBookEntity> rentalBooks) {
        if (CollectionUtils.isEmpty(rentalBooks)) {
            return Collections.emptyList();
        }
        return rentalBooks.stream()
                .map(this::createDto)
                .collect(Collectors.toList());
    }
}
