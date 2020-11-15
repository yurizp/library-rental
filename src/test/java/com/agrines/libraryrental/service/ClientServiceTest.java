package com.agrines.libraryrental.service;

import com.agrines.libraryrental.dto.ClientDto;
import com.agrines.libraryrental.entity.ClientEntity;
import com.agrines.libraryrental.enums.GenderEnum;
import com.agrines.libraryrental.mapper.ClientMapper;
import com.agrines.libraryrental.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository repository;
    @Mock
    private ClientMapper mapper;
    @InjectMocks
    private ClientService clientService;

    @Test
    public void shouldReturnClientWhenFindClientById() {
        long clientId = 4582L;
        ClientEntity clientEntity = new ClientEntity();
        ClientDto clientDto = createClientDto();
        when(repository.findById(clientId)).thenReturn(Optional.of(clientEntity));
        when(mapper.createClientDto(any())).thenReturn(clientDto);

        ClientDto result = clientService.findClientById(clientId);
        ClientDto expected = createClientDto();
        assertAll(
                () -> assertEquals(result.getName(), expected.getName()),
                () -> assertEquals(result.getGender(), expected.getGender()),
                () -> assertEquals(result.getBirthday(), expected.getBirthday()),
                () -> assertEquals(result.getId(), expected.getId()));
    }

    private ClientDto createClientDto() {
        return ClientDto.builder()
                .birthday(LocalDate.now().minusYears(10))
                .gender(GenderEnum.DRAG_KING)
                .name("Name")
                .id(4542L)
                .build();
    }
}