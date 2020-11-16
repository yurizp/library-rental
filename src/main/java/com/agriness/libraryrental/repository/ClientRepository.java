package com.agriness.libraryrental.repository;

import com.agriness.libraryrental.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
}
