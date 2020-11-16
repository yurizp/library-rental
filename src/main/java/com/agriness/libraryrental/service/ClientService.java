package com.agriness.libraryrental.service;

import com.agriness.libraryrental.dto.ClientDto;
import com.agriness.libraryrental.exception.ErrorMessage;
import com.agriness.libraryrental.exception.NotFoundException;
import com.agriness.libraryrental.mapper.ClientMapper;
import com.agriness.libraryrental.repository.ClientRepository;
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
