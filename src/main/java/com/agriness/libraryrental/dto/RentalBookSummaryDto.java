package com.agriness.libraryrental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate renteDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    private TaxDto tax;
    private BookSummaryDto bookSummary;

}
