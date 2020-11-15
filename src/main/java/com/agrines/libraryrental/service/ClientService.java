package com.agrines.libraryrental.service;

import com.agrines.libraryrental.dto.ClientDto;
import com.agrines.libraryrental.exception.ErrorMessage;
import com.agrines.libraryrental.exception.NotFoundException;
import com.agrines.libraryrental.mapper.ClientMapper;
import com.agrines.libraryrental.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository repository;
    private ClientMapper mapper;

    public ClientDto findClientById(Long clientId) {
        return repository.findById(clientId)
                .map(mapper::createClientDto)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.CLIENT_NOT_FOUND));
    }
}
