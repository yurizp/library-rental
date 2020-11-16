package com.agriness.libraryrental.dto;

import com.agriness.libraryrental.enums.GenderEnum;
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
public class ClientDto {

    private Long id;
    private String name;
    private LocalDate birthday;
    private GenderEnum gender;

}
