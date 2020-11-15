package com.agrines.libraryrental.mapper;

import com.agrines.libraryrental.entity.RentalBookClientEntity;
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
