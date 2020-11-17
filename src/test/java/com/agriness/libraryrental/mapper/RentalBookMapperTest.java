package com.agriness.libraryrental.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import com.agriness.libraryrental.dto.BookSummaryDto;
import com.agriness.libraryrental.dto.RentalBookDto;
import com.agriness.libraryrental.dto.RentalBookSummaryDto;
import com.agriness.libraryrental.dto.TaxDto;
import com.agriness.libraryrental.entity.RentalBookClientEntity;
import com.agriness.libraryrental.entity.RentalBookEntity;
import com.agriness.libraryrental.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RentalBookMapperTest {

    @Mock
    private BookSummaryMapper bookSummaryMapper;
    @Mock
    private TaxMapper taxMapper;
    @InjectMocks
    private RentalBookMapper mapper;

    @Test
    void shouldConvertBookPropertiesEntityToBookPropertiesDto() {
        RentalBookEntity entity = createRentalBookEntity();
        RentalBookDto result = mapper.createDto(entity);
        assertAll(
                () -> assertEquals(result.getId(), entity.getId()),
                () -> assertEquals(result.getStatus(), entity.getStatus()));

    }

    @Test
    void shouldConvertListBookPropertiesEntityToListBookPropertiesDto() {
        RentalBookEntity entity = createRentalBookEntity();
        List<RentalBookDto> results = mapper.createDto(Arrays.asList(entity));
        assertAll(
                () -> assertFalse(CollectionUtils.isEmpty(results)),
                () -> assertEquals(results.get(0).getId(), entity.getId()),
                () -> assertEquals(results.get(0).getStatus(), entity.getStatus()));

    }

    @Test
    void shouldReturnEmptyListWhenListBookPropertiesEntityIsNull() {
        List<RentalBookDto> results = mapper.createDto((List) null);
        assertTrue(CollectionUtils.isEmpty(results));
    }

    @Test
    void shouldReturnEmptyListWhenListBookPropertiesEntityIsEmpty() {
        List<RentalBookDto> results = mapper.createDto(Collections.EMPTY_LIST);
        assertTrue(CollectionUtils.isEmpty(results));
    }

    @Test
    void shouldcreateRentalBookDto() {
        RentalBookClientEntity entity = createRentalBookClientEntity();
        when(taxMapper.creteTaxDto(any(), anyLong())).thenReturn(new TaxDto());
        when(bookSummaryMapper.createBookSummaryDto(any())).thenReturn(new BookSummaryDto());
        RentalBookSummaryDto result = mapper.createRentalBookDto(entity, null);
        assertAll(
                () -> assertEquals(result.getRenteDate(), entity.getLentedDate()),
                () -> assertNotNull(result.getTax()),
                () -> assertNotNull(result.getBookSummary()),
                () -> assertEquals(result.getReturnDate(), entity.getReturnedDate()));
    }

    private RentalBookClientEntity createRentalBookClientEntity() {
        return RentalBookClientEntity.builder()
                .lentedDate(LocalDate.of(2020, 01, 01))
                .returnedDate(LocalDate.of(2020, 01, 04))
                .rentalBook(new RentalBookEntity())
                .build();
    }

    private RentalBookEntity createRentalBookEntity() {
        return RentalBookEntity.builder()
                .bookId(123L)
                .id(34L)
                .status(StatusEnum.AVAILABLE)
                .build();
    }
}