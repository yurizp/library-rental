package com.agriness.libraryrental.mapper;

import com.agriness.libraryrental.entity.RentalBookClientEntity;
import org.springframework.stereotype.Component;

@Component
public class RentalBookClientMapper {
    public RentalBookClientEntity createEntity(Long rentalBookId, Long clientId) {
        return RentalBookClientEntity.builder()
                .clientId(clientId)
                .rentalBookId(rentalBookId)
                .build();
    }
}
