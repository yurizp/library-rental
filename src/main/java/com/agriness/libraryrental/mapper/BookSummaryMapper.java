package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.BookSummaryDto;
import com.agriness.libraryrental.entity.BookEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookSummaryMapper {

    private BookPropertyMapper bookPropertieMapper;

    public BookSummaryDto createBookSummaryDto(BookEntity book) {
        return BookSummaryDto.builder()
                .description(book.getDescription())
                .id(book.getId())
                .isbn10(book.getIsbn10())
                .isbn13(book.getIsbn13())
                .properties(bookPropertieMapper.createDto(book.getProperties()))
                .title(book.getTitle())
                .build();
    }
}
