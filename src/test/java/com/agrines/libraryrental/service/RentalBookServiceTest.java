package com.agrines.libraryrental.service;

import com.agrines.libraryrental.dto.ClientDto;
import com.agrines.libraryrental.entity.RentalBookClientEntity;
import com.agrines.libraryrental.entity.RentalBookEntity;
import com.agrines.libraryrental.enums.StatusEnum;
import com.agrines.libraryrental.exception.ErrorMessage;
import com.agrines.libraryrental.exception.NotFoundException;
import com.agrines.libraryrental.mapper.RentalBookClientMapper;
import com.agrines.libraryrental.repository.RentalBookClientRepository;
import com.agrines.libraryrental.repository.RentalBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

}