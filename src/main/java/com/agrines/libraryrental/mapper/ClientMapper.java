package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.ClientDto;
import com.agrines.libraryrental.entity.ClientEntity;
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
