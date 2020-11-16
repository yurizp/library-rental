package com.agriness.libraryrental.repository;

import com.agriness.libraryrental.entity.RentalBookClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentalBookClientRepository extends CrudRepository<RentalBookClientEntity, Long> {

    Optional<List<RentalBookClientEntity>> findByClientId(Long clientId);
}
