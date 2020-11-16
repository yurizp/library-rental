package com.agriness.libraryrental.controller;

import com.agriness.libraryrental.controller.request.ReserveClientRequest;
import com.agriness.libraryrental.dto.BookDto;
import com.agriness.libraryrental.service.BookService;
import com.agriness.libraryrental.service.RentalBookService;
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
    @ApiOperation(value = "Retorna o todos os livros existentes.", notes = "Retorna o todos os livros existentes.")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PostMapping("/{bookId}/reserve/")
    @ApiOperation(value = "Reserva um livro para um cliente.", notes = "Reserva um livro para um cliente.")
    public ResponseEntity reserveBook(@PathVariable Long bookId, @RequestBody ReserveClientRequest body) {
        rentalBookService.rentBook(bookId, body.getClientId());
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/{bookId}/return/")
    @ApiOperation(value = "Devolve o livro.", notes = "Devolve o livro.")
    public ResponseEntity returnBook(@PathVariable Long bookId, @RequestBody ReserveClientRequest body) {
        rentalBookService.returnRentedBook(bookId, body.getClientId());
        return ResponseEntity.accepted().build();
    }
}
