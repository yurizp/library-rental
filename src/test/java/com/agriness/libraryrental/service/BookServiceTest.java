package com.agriness.libraryrental.service;

import com.agriness.libraryrental.dto.BookDto;
import com.agriness.libraryrental.entity.BookEntity;
import com.agriness.libraryrental.mapper.BookMapper;
import com.agriness.libraryrental.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;
    @Mock
    private BookMapper mapper;
    @InjectMocks
    private BookService service;

    @Test
    void shoudReturnAllBooks() {
        BookEntity bookEntity = new BookEntity();
        BookDto bookDto = new BookDto();
        when(repository.findAll()).thenReturn(Arrays.asList(bookEntity));
        when(mapper.createBookDto(bookEntity)).thenReturn(bookDto);
        List<BookDto> allBook = service.getAllBook();
        assertThat(allBook).containsExactlyInAnyOrder(bookDto);
    }

}