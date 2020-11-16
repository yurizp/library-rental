package com.agriness.libraryrental.repository;

import com.agriness.libraryrental.entity.RentalBookClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RentalBookClientRepository extends CrudRepository<RentalBookClientEntity, Long> {

    Optional<List<RentalBookClientEntity>> findByClientId(Long clientId);

    @Query(value = "SELECT rental_book_client.* FROM rental_book_client left join rental_book on rental_book_client.rental_book_id = rental_book.id\n" +
            "where book_id = :bookId AND client_id = :clientId and returned_date is null", nativeQuery = true)
    Optional<RentalBookClientEntity> findFirstByBooIdAndClientId(@Param("bookId") Long bookId, @Param("clientId") Long clientId);

}
