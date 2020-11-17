package com.agriness.libraryrental.service;


import com.agriness.libraryrental.dto.ClientDto;
import com.agriness.libraryrental.dto.RentalBookSummaryDto;
import com.agriness.libraryrental.entity.RentalBookClientEntity;
import com.agriness.libraryrental.entity.RentalBookEntity;
import com.agriness.libraryrental.entity.RentalTaxEntity;
import com.agriness.libraryrental.entity.RentalTaxRepository;
import com.agriness.libraryrental.enums.StatusEnum;
import com.agriness.libraryrental.exception.BadRequestException;
import com.agriness.libraryrental.exception.ErrorMessage;
import com.agriness.libraryrental.exception.NotFoundException;
import com.agriness.libraryrental.mapper.RentalBookClientMapper;
import com.agriness.libraryrental.mapper.RentalBookMapper;
import com.agriness.libraryrental.repository.RentalBookClientRepository;
import com.agriness.libraryrental.repository.RentalBookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if (bookClientRepository.findFirstByBooIdAndClientId(bookId, clientId).isPresent()) {
            throw new BadRequestException(ErrorMessage.ALREDY_RENTED_BOOK);
        }
        ClientDto client = clientService.findClientById(clientId);
        RentalBookClientEntity rentalBookClientEntity = rentalBookClientMapper.createEntity(rentalBookEntity.getId(), client.getId());
        rentalBookEntity.setStatus(StatusEnum.RENTED);
        repository.save(rentalBookEntity);
        bookClientRepository.save(rentalBookClientEntity);
    }


    public List<RentalBookSummaryDto> getRentalBooksByClientId(Long clientId) {
        return bookClientRepository.findByClientId(clientId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.RENTAL_BOOK_NOT_FOUND))
                .stream()
                .map(this::createRentalBookSummaryDto)
                .collect(Collectors.toList());
    }

    public void returnRentedBook(Long bookId, Long clientId) {
        RentalBookClientEntity rentalBookClientEntity = bookClientRepository.findFirstByBooIdAndClientId(bookId, clientId)
                .orElseThrow(() -> new BadRequestException(ErrorMessage.RENTAL_BOOK_NOT_FOUND));
        rentalBookClientEntity.setReturnedDate(LocalDate.now());
        rentalBookClientEntity.getRentalBook().setStatus(StatusEnum.AVAILABLE);
        repository.save(rentalBookClientEntity.getRentalBook());
        bookClientRepository.save(rentalBookClientEntity);
    }

    private RentalBookSummaryDto createRentalBookSummaryDto(RentalBookClientEntity rental) {
        RentalTaxEntity tax = bookTaxEntity.findTaxByRentalDate(ChronoUnit.DAYS.between(rental.getLentedDate(), rental.getReturnedDateOrToday()));
        return rentalBookMapper.createRentalBookDto(rental, tax);
    }
}
