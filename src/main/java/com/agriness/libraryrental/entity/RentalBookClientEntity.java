package com.agriness.libraryrental.entity;

import com.agriness.libraryrental.config.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.util.Objects;

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
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_book_id", referencedColumnName = "id", updatable = false, insertable = false)
    private RentalBookEntity rentalBook;

    @PrePersist
    public void prePersist() {
        lentedDate = LocalDate.now();
    }


    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }

    public LocalDate getReturnedDateOrToday() {
        return Objects.isNull(this.returnedDate) ? LocalDate.now() : this.returnedDate;
    }
}
