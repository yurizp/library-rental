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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RentalBookService {

    private RentalBookRepository repository;
    private RentalBookClientRepository bookClientRepository;
    private ClientService clientService;
    private RentalBookClientMapper rentalBookClientMapper;

    public void rentBook(Long bookId, Long clientId) {
        RentalBookEntity rentalBookEntity = repository.findFirstByBookIdAndStatus(bookId, StatusEnum.AVAILABLE)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.UNAVIABLE_RENT_BOOK));
        ClientDto client = clientService.findClientById(clientId);
        RentalBookClientEntity rentalBookClientEntity = rentalBookClientMapper.createEntity(rentalBookEntity.getId(), client.getId());
        rentalBookEntity.setStatus(StatusEnum.RENTED);
        repository.save(rentalBookEntity);
        bookClientRepository.save(rentalBookClientEntity);
    }
}
