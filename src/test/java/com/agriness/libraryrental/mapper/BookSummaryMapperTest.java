package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.BookSummaryDto;
import com.agriness.libraryrental.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookSummaryMapperTest {

    @Mock
    private BookPropertyMapper bookPropertieMapper;
    @InjectMocks
    private BookSummaryMapper bookSummaryMapper;

    @Test
    void shouldCreateBookSummaryDto() {
        BookEntity bookEntity = createBookEntity();
        when(bookPropertieMapper.createDto(anyList())).thenReturn(new ArrayList<>());
        BookSummaryDto result = bookSummaryMapper.createBookSummaryDto(bookEntity);
        assertAll(
                () -> assertEquals(result.getDescription(), bookEntity.getDescription()),
                () -> assertEquals(result.getIsbn10(), bookEntity.getIsbn10()),
                () -> assertEquals(result.getIsbn13(), bookEntity.getIsbn13()),
                () -> assertEquals(result.getTitle(), bookEntity.getTitle()),
                () -> assertTrue(CollectionUtils.isEmpty(result.getProperties())),
                () -> assertEquals(result.getId(), bookEntity.getId())
        );
    }

    private BookEntity createBookEntity() {
        return BookEntity.builder()
                .id(42L)
                .description("Description")
                .isbn10("isbn10")
                .isbn13("isbn13")
                .title("title")
                .properties(Collections.EMPTY_LIST)
                .rentalBooks(Collections.EMPTY_LIST)
                .build();
    }
}