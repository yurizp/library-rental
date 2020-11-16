package com.agrines.libraryrental.controller;

import com.agrines.libraryrental.dto.RentalBookSummaryDto;
import com.agrines.libraryrental.service.RentalBookService;
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
    public ResponseEntity<List<RentalBookSummaryDto>> reserveBook(@PathVariable Long clientId) {
        List<RentalBookSummaryDto> rentalBooksDtos = service.getRentalBooks(clientId);
        return ResponseEntity.ok(rentalBooksDtos);
    }
}
