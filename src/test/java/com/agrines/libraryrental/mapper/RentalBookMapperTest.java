package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.RentalBookDto;
import com.agrines.libraryrental.entity.RentalBookEntity;
import com.agrines.libraryrental.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class RentalBookMapperTest {

    @InjectMocks
    private RentalBookMapper mapper;


    @Test
    public void shouldConvertBookPropertiesEntityToBookPropertiesDto() {
        RentalBookEntity entity = createRentalBookEntity();
        RentalBookDto result = mapper.createDto(entity);
        assertAll(
                () -> assertEquals(result.getId(), entity.getId()),
                () -> assertEquals(result.getStatus(), entity.getStatus()));

    }

    @Test
    public void shouldConvertListBookPropertiesEntityToListBookPropertiesDto() {
        RentalBookEntity entity = createRentalBookEntity();
        List<RentalBookDto> results = mapper.createDto(Arrays.asList(entity));
        assertAll(
                () -> assertFalse(CollectionUtils.isEmpty(results)),
                () -> assertEquals(results.get(0).getId(), entity.getId()),
                () -> assertEquals(results.get(0).getStatus(), entity.getStatus()));

    }

    @Test
    public void shouldReturnEmptyListWhenListBookPropertiesEntityIsNull() {
        List<RentalBookDto> results = mapper.createDto((List) null);
        assertTrue(CollectionUtils.isEmpty(results));
    }

    @Test
    public void shouldReturnEmptyListWhenListBookPropertiesEntityIsEmpty() {
        List<RentalBookDto> results = mapper.createDto(Collections.EMPTY_LIST);
        assertTrue(CollectionUtils.isEmpty(results));
    }

    private RentalBookEntity createRentalBookEntity() {
        return RentalBookEntity.builder()
                .bookId(123L)
                .id(34L)
                .status(StatusEnum.AVAILABLE)
                .build();
    }
}