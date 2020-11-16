package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.TaxDto;
import com.agrines.libraryrental.entity.RentalTaxEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaxMapper {
    public TaxDto creteTaxDto(RentalTaxEntity rentalTax, Long totalDays) {
        return TaxDto.builder()
                .dailyRate(rentalTax.getDailyRate())
                .daysArrear(rentalTax.getDaysArrear())
                .description(rentalTax.getDescription())
                .penalty(rentalTax.getPenalty())
                .totalTax(getTotal(rentalTax,totalDays))
                .build();
    }

    private Double getTotal(RentalTaxEntity taxEntity, Long total) {
        Double totalDay = taxEntity.getDailyRate() * total;
        return totalDay + taxEntity.getPenalty();
    }
}
