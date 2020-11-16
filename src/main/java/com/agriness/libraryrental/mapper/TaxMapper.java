package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.dto.TaxDto;
import com.agriness.libraryrental.entity.RentalTaxEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class TaxMapper {
    public TaxDto creteTaxDto(RentalTaxEntity rentalTax, Long totalDays) {
        if(Objects.isNull(rentalTax)){
            return null;
        }
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
