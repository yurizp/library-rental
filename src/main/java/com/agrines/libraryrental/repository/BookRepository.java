package com.agrines.libraryrental.repository;

import com.agrines.libraryrental.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findAll();
}
