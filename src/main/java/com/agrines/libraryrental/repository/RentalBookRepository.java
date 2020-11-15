package com.agrines.libraryrental.repository;

import com.agrines.libraryrental.entity.RentalBookEntity;
import com.agrines.libraryrental.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentalBookRepository extends CrudRepository<RentalBookEntity, Long> {

    Optional<RentalBookEntity> findFirstByBookIdAndStatus(Long bookId, StatusEnum status);
}
