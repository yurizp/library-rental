package com.agriness.libraryrental.dto;

import com.agriness.libraryrental.enums.StatusEnum;
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
public class RentalBookDto {

    private Long id;
    private StatusEnum status;
}
