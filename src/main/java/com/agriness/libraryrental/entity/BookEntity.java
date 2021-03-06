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
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book")
public class BookEntity {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "isbn_10")
    private String isbn10;
    @Column(name = "isbn_13")
    private String isbn13;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", updatable = false, insertable = false)
    private List<RentalBookEntity> rentalBooks;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", updatable = false, insertable = false)
    private List<BookPropertyEntity> properties;

    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }
}
