package com.agrines.libraryrental.controller;

import com.agrines.libraryrental.controller.request.ReserveClientRequest;
import com.agrines.libraryrental.dto.BookDto;
import com.agrines.libraryrental.service.BookService;
import com.agrines.libraryrental.service.RentalBookService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/books")
public class BookController {

    private BookService bookService;
    private RentalBookService rentalBookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping("/{bookId}/reserve/")
    public ResponseEntity<List<BookDto>> reserveBook(@PathVariable Long bookId,
                                                     @RequestBody ReserveClientRequest body) {
        rentalBookService.rentBook(bookId, body.getClientId());
        return ResponseEntity.accepted().build();
    }
}
