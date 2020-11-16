package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.BookDto;
import com.agriness.libraryrental.entity.BookEntity;
import com.agriness.libraryrental.entity.RentalBookEntity;
import com.agriness.libraryrental.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookMapperTest {

    @Mock
    private RentalBookMapper rentalBookMapper;
    @Mock
    private BookPropertyMapper bookPropertieMapper;
    @InjectMocks
    private BookMapper bookMapper;


    @Test
    public void shouldConvertBookEntityToBookDtoAndSetStatusToUnavailable() {
        BookEntity bookEntity = createBookEntity();
        BookDto result = bookMapper.createBookDto(bookEntity);
        assertAll(
                () -> assertEquals(result.getId(), bookEntity.getId()),
                () -> assertEquals(result.getIsbn10(), bookEntity.getIsbn10()),
                () -> assertEquals(result.getIsbn13(), bookEntity.getIsbn13()),
                () -> assertEquals(result.getStatus(), StatusEnum.UNAVAILABLE),
                () -> assertEquals(result.getTitle(), bookEntity.getTitle()),
                () -> assertTrue(CollectionUtils.isEmpty(result.getProperties())),
                () -> assertTrue(CollectionUtils.isEmpty(result.getRentalBooks())),
                () -> assertEquals(result.getDescription(), bookEntity.getDescription()));
        verify(rentalBookMapper).createDto(nullable(List.class));
        verify(bookPropertieMapper).createDto(nullable(List.class));
    }

    @Test
    public void shouldConvertBookEntityToBookDtoAndSetStatusToAvailable() {
        BookEntity bookEntity = createBookEntity();
        bookEntity.setRentalBooks(Arrays.asList(
                RentalBookEntity.builder().status(StatusEnum.RENTED).build(),
                RentalBookEntity.builder().status(StatusEnum.AVAILABLE).build()
        ));
        BookDto result = bookMapper.createBookDto(bookEntity);
        assertAll(
                () -> assertEquals(result.getId(), bookEntity.getId()),
                () -> assertEquals(result.getIsbn10(), bookEntity.getIsbn10()),
                () -> assertEquals(result.getIsbn13(), bookEntity.getIsbn13()),
                () -> assertEquals(result.getStatus(), StatusEnum.AVAILABLE),
                () -> assertEquals(result.getTitle(), bookEntity.getTitle()),
                () -> assertTrue(CollectionUtils.isEmpty(result.getProperties())),
                () -> assertTrue(CollectionUtils.isEmpty(result.getRentalBooks())),
                () -> assertEquals(result.getDescription(), bookEntity.getDescription()));
        verify(rentalBookMapper).createDto(nullable(List.class));
        verify(bookPropertieMapper).createDto(nullable(List.class));
    }

    @Test
    public void shouldConvertBookEntityToBookDtoAndSetStatusToRented() {
        BookEntity bookEntity = createBookEntity();
        bookEntity.setRentalBooks(Arrays.asList(
                RentalBookEntity.builder().status(StatusEnum.RENTED).build(),
                RentalBookEntity.builder().status(StatusEnum.RENTED).build()
        ));
        BookDto result = bookMapper.createBookDto(bookEntity);
        assertAll(
                () -> assertEquals(result.getId(), bookEntity.getId()),
                () -> assertEquals(result.getIsbn10(), bookEntity.getIsbn10()),
                () -> assertEquals(result.getIsbn13(), bookEntity.getIsbn13()),
                () -> assertEquals(result.getStatus(), StatusEnum.RENTED),
                () -> assertEquals(result.getTitle(), bookEntity.getTitle()),
                () -> assertTrue(CollectionUtils.isEmpty(result.getProperties())),
                () -> assertTrue(CollectionUtils.isEmpty(result.getRentalBooks())),
                () -> assertEquals(result.getDescription(), bookEntity.getDescription()));
        verify(rentalBookMapper).createDto(nullable(List.class));
        verify(bookPropertieMapper).createDto(nullable(List.class));
    }

    private BookEntity createBookEntity() {
        return BookEntity.builder()
                .description("description")
                .id(123L)
                .isbn10("isbn10")
                .isbn13("isbn13")
                .title("title")
                .build();
    }
}