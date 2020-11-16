package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.entity.RentalBookClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RentalBookClientMapperTest {

    @InjectMocks
    private RentalBookClientMapper mapper;

    @Test
    public void shouldCreateRentalBOokClientEntity() {
        long clientId = 312L;
        long rentalBookId = 123L;
        RentalBookClientEntity result = mapper.createEntity(rentalBookId, clientId);
        assertAll(
                () -> assertEquals(result.getId(), null),
                () -> assertEquals(result.getRentalBookId(), rentalBookId),
                () -> assertEquals(result.getClientId(), clientId),
                () -> assertEquals(result.getReturnedDate(), null));
    }
}