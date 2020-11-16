package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.TaxDto;
import com.agrines.libraryrental.entity.RentalTaxEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TaxMapperTest {

    @InjectMocks
    private TaxMapper taxMapper;

    @Test
    public void shouldCreateTaxDto() {
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