package com.agrines.libraryrental.repository;

import com.agrines.libraryrental.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
}
