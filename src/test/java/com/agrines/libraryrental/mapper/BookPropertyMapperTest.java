package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.dto.BookPropertyDto;
import com.agrines.libraryrental.entity.BookPropertyEntity;
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
class BookPropertyMapperTest {

    @InjectMocks
    private BookPropertyMapper mapper;

    @Test
    public void shouldConvertBookPropertiesEntityToBookPropertiesDto() {
        BookPropertyEntity bookPropertyEntity = createBookPropertyEntity("titulo", "Sr Batata");
        BookPropertyDto result = mapper.createDto(bookPropertyEntity);
        assertAll(
                () -> assertEquals(result.getId(), bookPropertyEntity.getId()),
                () -> assertEquals(result.getKey(), bookPropertyEntity.getKey()),
                () -> assertEquals(result.getValue(), bookPropertyEntity.getValue()));

    }

    @Test
    public void shouldConvertListBookPropertiesEntityToListBookPropertiesDto() {
        BookPropertyEntity bookPropertyEntity = createBookPropertyEntity("titulo", "Sr Batata");
        List<BookPropertyDto> results = mapper.createDto(Arrays.asList(bookPropertyEntity));
        assertAll(
                () -> assertFalse(CollectionUtils.isEmpty(results)),
                () -> assertEquals(results.get(0).getId(), bookPropertyEntity.getId()),
                () -> assertEquals(results.get(0).getKey(), bookPropertyEntity.getKey()),
                () -> assertEquals(results.get(0).getValue(), bookPropertyEntity.getValue()));

    }

    @Test
    public void shouldReturnEmptyListWhenListBookPropertiesEntityIsNull() {
        List<BookPropertyDto> results = mapper.createDto((List) null);
        assertTrue(CollectionUtils.isEmpty(results));
    }

    @Test
    public void shouldReturnEmptyListWhenListBookPropertiesEntityIsEmpty() {
        List<BookPropertyDto> results = mapper.createDto(Collections.EMPTY_LIST);
        assertTrue(CollectionUtils.isEmpty(results));
    }

    private BookPropertyEntity createBookPropertyEntity(String key, String value) {
        return BookPropertyEntity.builder()
                .bookId(123L)
                .id(34L)
                .key(key)
                .value(value)
                .build();
    }
}