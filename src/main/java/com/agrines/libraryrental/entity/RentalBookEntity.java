package com.agrines.libraryrental.entity;

import com.agrines.libraryrental.config.ObjectUtils;
import com.agrines.libraryrental.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rental_book")
public class RentalBookEntity {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;
    @Column(name = "book_id")
    private Long bookId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", updatable = false, insertable = false)
    private BookEntity book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentalBookEntity)) return false;
        RentalBookEntity that = (RentalBookEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                getStatus() == that.getStatus() &&
                Objects.equals(getBookId(), that.getBookId());
    }

    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }
}
