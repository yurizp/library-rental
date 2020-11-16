package com.agriness.libraryrental.entity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalTaxRepository extends CrudRepository<RentalTaxEntity, Long> {

    @Query(value = "select * from rental_tax where days_arrear < :daysLate order by days_arrear desc limit 1", nativeQuery = true)
    RentalTaxEntity findTaxByRentalDate(@Param("daysLate") Long daysLate);
}
