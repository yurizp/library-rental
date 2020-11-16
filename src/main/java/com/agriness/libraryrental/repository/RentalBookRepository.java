package com.agriness.libraryrental.repository;

import com.agriness.libraryrental.entity.RentalBookEntity;
import com.agriness.libraryrental.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentalBookRepository extends CrudRepository<RentalBookEntity, Long> {

    Optional<RentalBookEntity> findFirstByBookIdAndStatus(Long bookId, StatusEnum status);

}
