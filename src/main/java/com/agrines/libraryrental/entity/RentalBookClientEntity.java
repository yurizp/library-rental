package com.agrines.libraryrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rental_book_client")
public class RentalBookClientEntity {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rental_book_id")
    private Long rentalBookId;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "lented_date")
    private LocalDate lentedDate;
    @Column(name = "returned_date")
    private LocalDate returnedDate;

    @PrePersist
    public void prePersist(){
        lentedDate = LocalDate.now();
    }
}
