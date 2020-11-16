package com.agriness.libraryrental.service;

import com.agriness.libraryrental.dto.BookDto;
import com.agriness.libraryrental.mapper.BookMapper;
import com.agriness.libraryrental.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private BookRepository repository;
    private BookMapper mapper;

    public List<BookDto> getAllBook() {
        return repository.findAll()
                .stream()
                .map(mapper::createBookDto)
                .collect(Collectors.toList());
    }
}
