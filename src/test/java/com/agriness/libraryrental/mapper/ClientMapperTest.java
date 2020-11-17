package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.ClientDto;
import com.agriness.libraryrental.entity.ClientEntity;
import com.agriness.libraryrental.enums.GenderEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {

    @InjectMocks
    private ClientMapper mapper;

    @Test
    void shouldCreateClientDto() {
        ClientEntity clientEntity = createClientEntity();
        ClientDto result = mapper.createClientDto(clientEntity);

        assertAll(
                () -> assertEquals(result.getId(), clientEntity.getId()),
                () -> assertEquals(result.getBirthday(), clientEntity.getBirthday()),
                () -> assertEquals(result.getName(), clientEntity.getName()),
                () -> assertEquals(result.getGender(), clientEntity.getGender()));
    }

    private ClientEntity createClientEntity() {
        return ClientEntity.builder()
                .birthday(LocalDate.now().minusYears(10))
                .gender(GenderEnum.DRAG_KING)
                .name("Name")
                .id(4542L)
                .build();
    }
}