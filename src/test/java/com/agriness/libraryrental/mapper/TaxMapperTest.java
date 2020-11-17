package com.agriness.libraryrental.mapper;

import static org.junit.jupiter.api.Assertions.*;
import com.agriness.libraryrental.dto.TaxDto;
import com.agriness.libraryrental.entity.RentalTaxEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaxMapperTest {

    @InjectMocks
    private TaxMapper taxMapper;

    @Test
    void shouldCreateTaxDto() {
        RentalTaxEntity rentalTaxEntity = createRentalTaxEntity();
        TaxDto result = taxMapper.creteTaxDto(rentalTaxEntity, 10L);
        assertAll(
                () -> assertEquals(result.getDescription(), rentalTaxEntity.getDescription()),
                () -> assertEquals(result.getDaysArrear(), rentalTaxEntity.getDaysArrear()),
                () -> assertEquals(result.getPenalty(), rentalTaxEntity.getPenalty()),
                () -> assertEquals(result.getTotalTax(), 13),
                () -> assertEquals(result.getDailyRate(), rentalTaxEntity.getDailyRate())
        );
    }

    @Test
    void shouldReturnNullWhenInputIsNull() {
        TaxDto result = taxMapper.creteTaxDto(null, 10L);
        assertNull(result);
    }

    private RentalTaxEntity createRentalTaxEntity() {
        return RentalTaxEntity.builder()
                .dailyRate(0.6D)
                .daysArrear(7)
                .description("description")
                .id(12L)
                .penalty(7D)
                .build();
    }
}