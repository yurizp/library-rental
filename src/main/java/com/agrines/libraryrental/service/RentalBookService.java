package com.agrines.libraryrental.service;


import com.agrines.libraryrental.dto.ClientDto;
import com.agrines.libraryrental.dto.RentalBookSummaryDto;
import com.agrines.libraryrental.entity.RentalBookClientEntity;
import com.agrines.libraryrental.entity.RentalBookEntity;
import com.agrines.libraryrental.entity.RentalTaxEntity;
import com.agrines.libraryrental.entity.RentalTaxRepository;
import com.agrines.libraryrental.enums.StatusEnum;
import com.agrines.libraryrental.exception.ErrorMessage;
import com.agrines.libraryrental.exception.NotFoundException;
import com.agrines.libraryrental.mapper.RentalBookClientMapper;
import com.agrines.libraryrental.mapper.RentalBookMapper;
import com.agrines.libraryrental.repository.RentalBookClientRepository;
import com.agrines.libraryrental.repository.RentalBookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RentalBookService {

    private RentalBookRepository repository;
    private RentalBookClientRepository bookClientRepository;
    private ClientService clientService;
    private RentalTaxRepository bookTaxEntity;
    private RentalBookClientMapper rentalBookClientMapper;
    private RentalBookMapper rentalBookMapper;

    public void rentBook(Long bookId, Long clientId) {
        RentalBookEntity rentalBookEntity = repository.findFirstByBookIdAndStatus(bookId, StatusEnum.AVAILABLE)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.UNAVIABLE_RENT_BOOK));
        ClientDto client = clientService.findClientById(clientId);
        RentalBookClientEntity rentalBookClientEntity = rentalBookClientMapper.createEntity(rentalBookEntity.getId(), client.getId());
        rentalBookEntity.setStatus(StatusEnum.RENTED);
        repository.save(rentalBookEntity);
        bookClientRepository.save(rentalBookClientEntity);
    }


    public List<RentalBookSummaryDto> getRentalBooks(Long clientId) {
        return bookClientRepository.findByClientId(clientId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.RENTAL_BOOK_NOT_FOUND))
                .stream()
                .map(rental -> createRentalBookSummaryDto(rental))
                .collect(Collectors.toList());
    }

    private RentalBookSummaryDto createRentalBookSummaryDto(RentalBookClientEntity rental) {
        RentalTaxEntity tax = bookTaxEntity.findTaxByRentalDate(ChronoUnit.DAYS.between(rental.getLentedDate(), rental.getReturnedDateOrToday()));
        return rentalBookMapper.createRentalBookDto(rental, tax);
    }

}
