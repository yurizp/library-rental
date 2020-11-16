package com.agrines.libraryrental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxDto {

    private String description;
    private Double dailyRate;
    private Double penalty;
    private Integer daysArrear;
    private Double totalTax;
}
