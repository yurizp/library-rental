package com.agrines.libraryrental.dto;

import com.agrines.libraryrental.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalBookSummaryDto {

    private LocalDate renteDate;
    private LocalDate returnDate;
    private TaxDto tax;
    private BookSummaryDto bookSummary;

}
