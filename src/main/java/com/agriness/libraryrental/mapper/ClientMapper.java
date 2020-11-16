package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.ClientDto;
import com.agriness.libraryrental.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto createClientDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .birthday(clientEntity.getBirthday())
                .gender(clientEntity.getGender())
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .build();
    }
}
