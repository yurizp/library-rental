package com.agrines.libraryrental.controller;

import com.agrines.libraryrental.dto.BookDto;
import com.agrines.libraryrental.dto.BookPropertyDto;
import com.agrines.libraryrental.dto.RentalBookDto;
import com.agrines.libraryrental.enums.StatusEnum;
import com.agrines.libraryrental.service.BookService;
import com.agrines.libraryrental.service.RentalBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import utils.ResourceUtils;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc client;

    @MockBean
    private BookService service;

    @MockBean
    private RentalBookService rentalBookService;

    @Test
    public void shouldReturnAllBooks() throws Exception {
        String response = ResourceUtils.loadResourceAsString("json/book/get-all-books.json");
        when(service.getAllBook()).thenReturn(Arrays.asList(createBookDto()));
        client.perform(get("/v1/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(response));
    }

    @Test
    public void shouldReserve() throws Exception {
        client.perform(post("/v1/books/123/reserve/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"clientId\":333}"))
                .andExpect(status().is(202));
        verify(rentalBookService).rentBook(eq(123L), eq(333L));
    }

    private BookDto createBookDto() {
        return BookDto.builder()
                .id(483L)
                .title("title")
                .status(StatusEnum.AVAILABLE)
                .rentalBooks(Arrays.asList(createRentalBookDto()))
                .isbn13("isbn13")
                .isbn10("isbn10")
                .description("description")
                .properties(Arrays.asList(createBookPropertyDto()))
                .build();
    }

    private BookPropertyDto createBookPropertyDto() {
        return BookPropertyDto.builder()
                .value("value")
                .key("key")
                .id(189L)
                .build();
    }

    private RentalBookDto createRentalBookDto() {
        return RentalBookDto.builder()
                .status(StatusEnum.AVAILABLE)
                .id(1L)
                .build();
    }
}