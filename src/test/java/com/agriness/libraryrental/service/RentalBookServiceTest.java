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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalBookServiceTest {
    @Mock
    private RentalBookRepository repository;
    @Mock
    private RentalBookClientRepository bookClientRepository;
    @Mock
    private ClientService clientService;
    @Mock
    private RentalBookClientMapper rentalBookClientMapper;
    @Mock
    private RentalTaxRepository bookTaxEntity;
    @Mock
    private RentalBookMapper rentalBookMapper;
    @InjectMocks
    private RentalBookService service;

    @Test
    public void shoudlRenturBookWithSucess() {
        long clientId = 542L;
        long bookId = 123L;
        when(repository.findFirstByBookIdAndStatus(bookId, StatusEnum.AVAILABLE))
                .thenReturn(Optional.of(RentalBookEntity.builder().id(123L).build()));
        when(clientService.findClientById(clientId)).thenReturn(ClientDto.builder().id(clientId).build());
        when(rentalBookClientMapper.createEntity(eq(bookId), eq(clientId))).thenReturn(new RentalBookClientEntity());
        service.rentBook(bookId, clientId);
        RentalBookEntity rentalExpected = RentalBookEntity.builder().id(123L).status(StatusEnum.RENTED).build();
        verify(repository).save(eq(rentalExpected));
        verify(bookClientRepository).save(any(RentalBookClientEntity.class));
    }

    @Test
    public void shoudlRenturNotFoundWhenNoExistsAvailableBook() {
        long clientId = 542L;
        long bookId = 123L;
        when(repository.findFirstByBookIdAndStatus(bookId, StatusEnum.AVAILABLE)).thenReturn(Optional.empty());

        NotFoundException expected = new NotFoundException(ErrorMessage.UNAVIABLE_RENT_BOOK);
        NotFoundException caughtException = assertThrows(NotFoundException.class, () -> service.rentBook(bookId, clientId));
        assertAll(
                () -> assertEquals(caughtException.getError(), expected.getError()),
                () -> assertEquals(caughtException.getMessage(), expected.getMessage()),
                () -> assertEquals(caughtException.getStatus(), expected.getStatus())
        );

        verify(repository, never()).save(any());
        verify(bookClientRepository, never()).save(any());
    }

    @Test
    public void shoudlRenturBadRequestWhenAlredyRented() {
        long clientId = 542L;
        long bookId = 123L;
        when(repository.findFirstByBookIdAndStatus(bookId, StatusEnum.AVAILABLE))
                .thenReturn(Optional.of(RentalBookEntity.builder().id(123L).build()));
        when(bookClientRepository.findFirstByBooIdAndClientId(bookId, clientId)).thenReturn(Optional.of(new RentalBookClientEntity()));
        BadRequestException expected = new BadRequestException(ErrorMessage.ALREDY_RENTED_BOOK);
        BadRequestException caughtException = assertThrows(BadRequestException.class, () -> service.rentBook(bookId, clientId));
        assertAll(
                () -> assertEquals(caughtException.getError(), expected.getError()),
                () -> assertEquals(caughtException.getMessage(), expected.getMessage()),
                () -> assertEquals(caughtException.getStatus(), expected.getStatus())
        );

        verify(repository, never()).save(any());
        verify(bookClientRepository, never()).save(any());
    }

    @Test
    public void shouldReturnRentedBookWithSucess() {
        long clientId = 542L;
        long bookId = 123L;
        RentalBookClientEntity bookClientEntity = new RentalBookClientEntity();
        bookClientEntity.setRentalBook(RentalBookEntity.builder().status(StatusEnum.RENTED).build());
        when(bookClientRepository.findFirstByBooIdAndClientId(eq(bookId), eq(clientId))).thenReturn(Optional.of(bookClientEntity));
        service.returnRentedBook(bookId, clientId);
        RentalBookClientEntity expected = new RentalBookClientEntity();
        expected.setReturnedDate(LocalDate.now());
        expected.setRentalBook(RentalBookEntity.builder().status(StatusEnum.AVAILABLE).build());
        verify(repository).save(eq(expected.getRentalBook()));
        verify(bookClientRepository).save(any());
    }

    @Test
    public void shouldReturnBadRequestWhenNotFoundRentedBook() {
        long clientId = 542L;
        long bookId = 123L;
        when(bookClientRepository.findFirstByBooIdAndClientId(eq(bookId), eq(clientId))).thenReturn(Optional.empty());

        BadRequestException expected = new BadRequestException(ErrorMessage.RENTAL_BOOK_NOT_FOUND);
        BadRequestException caughtException = assertThrows(BadRequestException.class, () -> service.returnRentedBook(bookId, clientId));
        assertAll(
                () -> assertEquals(caughtException.getError(), expected.getError()),
                () -> assertEquals(caughtException.getMessage(), expected.getMessage()),
                () -> assertEquals(caughtException.getStatus(), expected.getStatus())
        );

        verify(repository, never()).save(any());
        verify(bookClientRepository, never()).save(any());
    }

    @Test
    public void shouldReturnRentalBooksByClientIdWithSucess() {
        long clientId = 123L;
        when(bookClientRepository.findByClientId(clientId)).thenReturn(Optional.of(Arrays.asList(RentalBookClientEntity.builder().lentedDate(LocalDate.now()).build())));
        when(bookTaxEntity.findTaxByRentalDate(anyLong())).thenReturn(new RentalTaxEntity());
        when(rentalBookMapper.createRentalBookDto(any(), any())).thenReturn(new RentalBookSummaryDto());
        List<RentalBookSummaryDto> result = service.getRentalBooksByClientId(clientId);
        assertFalse(CollectionUtils.isEmpty(result));
    }

}