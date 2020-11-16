package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.BookDto;
import com.agriness.libraryrental.entity.BookEntity;
import com.agriness.libraryrental.entity.RentalBookEntity;
import com.agriness.libraryrental.enums.StatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@AllArgsConstructor
public class BookMapper {

    private RentalBookMapper rentalBookMapper;
    private BookPropertyMapper bookPropertieMapper;

    public BookDto createBookDto(BookEntity bookEntity) {
        return BookDto.builder()
                .title(bookEntity.getTitle())
                .id(bookEntity.getId())
                .description(bookEntity.getDescription())
                .isbn10(bookEntity.getIsbn10())
                .isbn13(bookEntity.getIsbn13())
                .rentalBooks(rentalBookMapper.createDto(bookEntity.getRentalBooks()))
                .properties(bookPropertieMapper.createDto(bookEntity.getProperties()))
                .status(getStatus(bookEntity.getRentalBooks()))
                .build();
    }

    private StatusEnum getStatus(List<RentalBookEntity> rentalBooks) {
        if (CollectionUtils.isEmpty(rentalBooks)) {
            return StatusEnum.UNAVAILABLE;
        }
        return rentalBooks.stream()
                .filter(rental -> StatusEnum.AVAILABLE.equals(rental.getStatus()))
                .findFirst()
                .map(RentalBookEntity::getStatus)
                .orElse(StatusEnum.RENTED);
    }


}
