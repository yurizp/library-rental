package com.agriness.libraryrental.dto;

import com.agriness.libraryrental.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String description;
    private String isbn10;
    private String isbn13;
    private StatusEnum status;
    private List<RentalBookDto> rentalBooks;
    private List<BookPropertyDto> properties;
}
