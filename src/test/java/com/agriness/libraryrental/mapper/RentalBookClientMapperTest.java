package com.agriness.libraryrental.mapper;

import static org.junit.jupiter.api.Assertions.*;
import com.agriness.libraryrental.entity.RentalBookClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RentalBookClientMapperTest {

    @InjectMocks
    private RentalBookClientMapper mapper;

    @Test
    void shouldCreateRentalBOokClientEntity() {
        long clientId = 312L;
        long rentalBookId = 123L;
        RentalBookClientEntity result = mapper.createEntity(rentalBookId, clientId);
        assertAll(
                () -> assertNull(result.getId()),
                () -> assertEquals(result.getRentalBookId(), rentalBookId),
                () -> assertEquals(result.getClientId(), clientId),
                () -> assertNull(result.getReturnedDate()));
    }
}