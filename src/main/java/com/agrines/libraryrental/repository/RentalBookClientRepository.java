package com.agrines.libraryrental.repository;

import com.agrines.libraryrental.entity.RentalBookClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentalBookClientRepository extends CrudRepository<RentalBookClientEntity, Long> {

    Optional<List<RentalBookClientEntity>> findByClientId(Long clientId);
}
