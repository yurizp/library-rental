package com.agriness.libraryrental.controller;

import com.agriness.libraryrental.dto.RentalBookSummaryDto;
import com.agriness.libraryrental.service.RentalBookService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/clients")
public class ClientController {

    private RentalBookService service;

    @GetMapping("/{clientId}/books/")
    @ApiOperation(value = "Retorna o historico de livros do cliente pelo id.", notes = "Retorna o historico de livros do cliente pelo id.")
    public ResponseEntity<List<RentalBookSummaryDto>> reserveBook(@PathVariable Long clientId) {
        List<RentalBookSummaryDto> rentalBooksDtos = service.
                getRentalBooksByClientId(clientId);
        return ResponseEntity.ok(rentalBooksDtos);
    }
}
