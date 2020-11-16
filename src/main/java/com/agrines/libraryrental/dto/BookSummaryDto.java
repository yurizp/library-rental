package com.agrines.libraryrental.dto;

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
public class BookSummaryDto {

    private Long id;
    private String title;
    private String description;
    private String isbn10;
    private String isbn13;
    private List<BookPropertyDto> properties;

}
