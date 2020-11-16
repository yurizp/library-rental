package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.RentalBookDto;
import com.agrines.libraryrental.dto.RentalBookSummaryDto;
import com.agrines.libraryrental.entity.RentalBookClientEntity;
import com.agrines.libraryrental.entity.RentalBookEntity;
import com.agrines.libraryrental.entity.RentalTaxEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentalBookMapper {

    private BookSummaryMapper mapper;
    private TaxMapper taxMapper;

    public RentalBookDto createDto(RentalBookEntity rentalBooks) {
        return RentalBookDto.builder()
                .id(rentalBooks.getId())
                .status(rentalBooks.getStatus())
                .build();
    }

    public List<RentalBookDto> createDto(List<RentalBookEntity> rentalBooks) {
        if (CollectionUtils.isEmpty(rentalBooks)) {
            return Collections.emptyList();
        }
        return rentalBooks.stream()
                .map(this::createDto)
                .collect(Collectors.toList());
    }

    public RentalBookSummaryDto createRentalBookDto(RentalBookClientEntity rentalEntity, RentalTaxEntity rentalTax) {
        return RentalBookSummaryDto.builder()
                .renteDate(rentalEntity.getLentedDate())
                .returnDate(rentalEntity.getReturnedDate())
                .bookSummary(mapper.createBookSummaryDto(rentalEntity.getRentalBook().getBook()))
                .tax(taxMapper.creteTaxDto(rentalTax, ChronoUnit.DAYS.between(rentalEntity.getLentedDate(), rentalEntity.getReturnedDateOrToday())))
                .build();
    }

}
